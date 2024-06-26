package core.fu4sbackend.service;

import core.fu4sbackend.constant.PostStatus;
import core.fu4sbackend.dto.LearningMaterialDto;
import core.fu4sbackend.entity.LearningMaterial;
import core.fu4sbackend.entity.MaterialFile;
import core.fu4sbackend.repository.LearningMaterialRepository;
import core.fu4sbackend.repository.MaterialFileRepository;
import core.fu4sbackend.repository.SubjectRepository;
import core.fu4sbackend.repository.UserRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LearningMaterialService {
    private final LearningMaterialRepository learningMaterialRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final MaterialFileRepository materialFileRepository;

    public LearningMaterialService(LearningMaterialRepository learningMaterialRepository, UserRepository userRepository, SubjectRepository subjectRepository, MaterialFileRepository materialFileRepository) {
        this.learningMaterialRepository = learningMaterialRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.materialFileRepository = materialFileRepository;
    }


//    public List<LearningMaterialDto> getAllLearningMaterials() {
//        List<LearningMaterial> learningMaterials = learningMaterialRepository.findAll();
//        List<LearningMaterialDto> learningMaterialDtos = new ArrayList<>();
//
//        ModelMapper modelMapper = new ModelMapper();
//
//        learningMaterialDtos = learningMaterials
//                .stream()
//                .map(learningMaterial -> {
//                    LearningMaterialDto learningMaterialDto = modelMapper.map(learningMaterial, LearningMaterialDto.class);
//                    learningMaterialDto.setUsername(learningMaterial.getUser().getFirstName() + " " + learningMaterial.getUser().getLastName());
//                    return learningMaterialDto;
//                })
//                .collect(Collectors.toList());
//        return learningMaterialDtos;
//    }

    public LearningMaterialDto getLearningMaterialById(Integer id) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        Optional<LearningMaterial> optionalLearningMaterial = learningMaterialRepository.findById(id);

        if (optionalLearningMaterial.isPresent()) {
            LearningMaterial learningMaterial = optionalLearningMaterial.get();
            LearningMaterialDto learningMaterialDto = modelMapper.map(learningMaterial, LearningMaterialDto.class);
            learningMaterialDto.setUsername(learningMaterial.getUser().getFirstName() + " " + learningMaterial.getUser().getLastName());
            return learningMaterialDto;
        } else {
            throw new Exception("Learning material not found with id: " + id);
        }
    }


//    public List<LearningMaterialDto> findByKeyword(String keyword){
//        List<LearningMaterial> learningMaterials = learningMaterialRepository.findByKeyword(keyword);
//        List<LearningMaterialDto> learningMaterialDtos;
//
//        ModelMapper modelMapper = new ModelMapper();
//        learningMaterialDtos = learningMaterials
//                .stream()
//                .map(learningMaterial -> {
//                    LearningMaterialDto learningMaterialDto =  modelMapper.map(learningMaterial, LearningMaterialDto.class);
//                    learningMaterialDto.setUsername(learningMaterial.getUser().getFirstName()+" "+learningMaterial.getUser().getLastName());
//
//                    return learningMaterialDto ;
//                })
//
//                .collect(Collectors.toList());
//
//        return learningMaterialDtos;
//    }

    public List<LearningMaterialDto> getLearningMaterialsByUsername(String username, Integer pageNum, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by("postTime").descending());

        ModelMapper modelMapper = new ModelMapper();
        return learningMaterialRepository.getAllByUsername(username, paging)
                .stream().map(learningMaterial -> modelMapper.map(learningMaterial, LearningMaterialDto.class))
                .toList();
    }

    public Integer getNumberOfLearningMaterials(String username) {
        return learningMaterialRepository.getAllByUsername(username, null).size();
    }

    @Transactional
    public LearningMaterialDto add(String title, String subjectCode, String content, List<MultipartFile> files, String username) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

        LearningMaterial learningMaterial = new LearningMaterial();
        learningMaterial.setUser(userRepository.findByUsername(username).orElseThrow());
        learningMaterial.setTitle(title);
        learningMaterial.setContent(content);
        learningMaterial.setPostTime(new Date(System.currentTimeMillis()));
        learningMaterial.setStatus(PostStatus.PENDING_APPROVE);
        learningMaterial.setSubject(subjectRepository.findById(subjectCode).orElseThrow());
        learningMaterial.setTest(false);

        learningMaterial = learningMaterialRepository.save(learningMaterial);
        if (files != null) for (MultipartFile file : files) {
            String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + learningMaterial.getId();
            String fileName = file.getOriginalFilename();
            try {
                if (fileName.contains("..")) {
                    throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
                }

                Path fileStorageLocation = Paths.get(filePath).toAbsolutePath().normalize();
                if (!Files.exists(fileStorageLocation)) {
                    Files.createDirectories(fileStorageLocation);
                }
                Path targetLocation = fileStorageLocation.resolve(fileName);

                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
            }

            MaterialFile materialFile = new MaterialFile();
            materialFile.setLearningMaterial(learningMaterial);
            materialFile.setFilename(file.getOriginalFilename());
            materialFileRepository.save(materialFile);
        }

        return modelMapper.map(learningMaterial, LearningMaterialDto.class);
    }

    public MaterialFile getFile(Integer fileId) {
        return materialFileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found with id " + fileId));
    }

    public List<MaterialFile> getFilesByMaterialId(Integer materialId) {
        return materialFileRepository.findByLearningMaterialId(materialId);
    }

    public LearningMaterialDto getById(Integer id) {
        ModelMapper modelMapper = new ModelMapper();
        LearningMaterial learningMaterial = learningMaterialRepository.findById(id).orElseThrow();

        List<String> list = new ArrayList<>();
        for (MaterialFile materialFile : learningMaterial.getFiles()) {
            list.add(materialFile.getFilename());
        }
        LearningMaterialDto learningMaterialDto = modelMapper.map(learningMaterial, LearningMaterialDto.class);
        learningMaterialDto.setFilenames(list);

        return learningMaterialDto;
    }

    public ResponseEntity<Resource> getFileOfMaterial(Integer id, String filename) throws IOException {
        LearningMaterial learningMaterial = learningMaterialRepository.findById(id).orElseThrow();
        MaterialFile materialFile = materialFileRepository.findByLearningMaterialAndFilename(learningMaterial, filename);

        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + learningMaterial.getId() + "\\" + materialFile.getFilename();
        Path path = Paths.get(filePath);

        File file = new File(filePath);
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(mimeType))
                .body(new UrlResource(path.toUri()));
    }

    public void deleteLearningMaterial(Integer id, String username) throws Exception {
        LearningMaterial learningMaterial = learningMaterialRepository.findById(id).orElseThrow();

        if (!learningMaterial.getUser().getUsername().equals(username)) {
            throw new Exception("Username mismatched!");
        }

        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + id;
        FileUtils.deleteDirectory(new File(filePath));

        learningMaterialRepository.delete(learningMaterial);
    }

    @Transactional
    public LearningMaterialDto edit(Integer id, String title, String subjectCode, String content, List<MultipartFile> files, String username, boolean deleteAllFiles) throws Exception {
        LearningMaterial learningMaterial = learningMaterialRepository.findById(id).orElseThrow();
        learningMaterial.setTitle(title);
        learningMaterial.setContent(content);
        learningMaterial.setPostTime(new Date(System.currentTimeMillis()));
        learningMaterial.setSubject(subjectRepository.findById(subjectCode).orElseThrow());
        learningMaterialRepository.save(learningMaterial);

        if (deleteAllFiles) {
            String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + id;
            FileUtils.deleteDirectory(new File(filePath));
            materialFileRepository.deleteByLearningMaterial(learningMaterial);
        } else {
            if(files == null) {
                // giu nguyen
            } else {
                materialFileRepository.deleteByLearningMaterial(learningMaterial);
                for(MultipartFile file : files) {
                    MaterialFile materialFile = new MaterialFile();
                    materialFile.setLearningMaterial(learningMaterial);
                    materialFile.setFilename(file.getOriginalFilename());
                    materialFileRepository.save(materialFile);
                }

                String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + id;
                FileUtils.deleteDirectory(new File(filePath));
                for (MultipartFile file : files) {
                    filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + learningMaterial.getId();
                    String fileName = file.getOriginalFilename();
                    try {
                        if (fileName.contains("..")) {
                            throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
                        }

                        Path fileStorageLocation = Paths.get(filePath).toAbsolutePath().normalize();
                        if (!Files.exists(fileStorageLocation)) {
                            Files.createDirectories(fileStorageLocation);
                        }
                        Path targetLocation = fileStorageLocation.resolve(fileName);

                        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                    }
                }
            }
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(learningMaterial, LearningMaterialDto.class);
    }
}