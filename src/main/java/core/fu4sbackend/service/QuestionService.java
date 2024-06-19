package core.fu4sbackend.service;

import core.fu4sbackend.dto.AnswerDto;
import core.fu4sbackend.dto.QuestionDto;
import core.fu4sbackend.helper.PriorityRangeList;
import core.fu4sbackend.repository.QuestionPriorityRepository;
import core.fu4sbackend.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final int PROBABILITY_GENERATOR_RANGE = 1000;
    private final QuestionRepository questionRepository;
    private final QuestionPriorityRepository questionPriorityRepository;
    private final QuestionPriorityService questionPriorityService;

    @Autowired
    public QuestionService(QuestionPriorityRepository questionPriorityRepository, QuestionRepository questionRepository, QuestionPriorityService questionPriorityService) {
        this.questionPriorityRepository = questionPriorityRepository;
        this.questionRepository = questionRepository;
        this.questionPriorityService = questionPriorityService;
    }

    public List<QuestionDto> getAllQuestionIdByUsernameAndQuestionSetIdAndPriority(String username, int questionSetId, int priority) {
        ModelMapper modelMapper = new ModelMapper();
        return questionPriorityRepository.findByQuestionSetIdAndUsernameAndPriority(questionSetId, username, priority).stream().map(
                questionPriority -> getById(questionPriority.getQuestion().getId())
        ).collect(Collectors.toList());
    }

    public List<QuestionDto> getByQuestionSetId(int questionSetId){
        ModelMapper modelMapper = new ModelMapper();
        return questionRepository.getByQuestionSetId(questionSetId).stream()
                .map(question -> {
                    QuestionDto questionDto = modelMapper.map(question,QuestionDto.class);
                    return questionDto;
                })
                .collect(Collectors.toList());
    }

    public List<QuestionDto> getByQuestionSetIdRandomly(int questionSetId, int numberOfQuestions, boolean isPersonalized, String username) {
        Random rand = new Random();
        List<QuestionDto> allQuestions = getByQuestionSetId(questionSetId);
        List<QuestionDto> randomQuestions = new ArrayList<>();

        if(!isPersonalized)
            addToRandomQuestions(rand,allQuestions,randomQuestions,numberOfQuestions);

        else{
            questionPriorityService.initiateQuestionPrioritiesByUsernameAndQuestionSetId(username, questionSetId);
            int generatedRandomNumber;
            int numberOfPartitions, partitionsPerLevel, rangePerPartition, previousBound;
            PriorityRangeList priorityRangeList = new PriorityRangeList();
            List<QuestionDto>[] allQuestionsByPriority = new List[QuestionPriorityService.MAX_QUESTION_PRIORITY+1];

            //Initialize question lists by priority
            for(int i = 0; i <= QuestionPriorityService.MAX_QUESTION_PRIORITY; i++){
                allQuestionsByPriority[i] = getAllQuestionIdByUsernameAndQuestionSetIdAndPriority(
                        username,
                        questionSetId,
                        i
                );

            }

            //picking questions to add
            for(int i=0;i<numberOfQuestions;i++){
                numberOfPartitions = 0;
                partitionsPerLevel = 1;
                previousBound = 0;
                priorityRangeList.clear();
                generatedRandomNumber = rand.nextInt(PROBABILITY_GENERATOR_RANGE);
                System.out.println(generatedRandomNumber);
                //calculate number of partitions should be created
                for(int j=QuestionPriorityService.MAX_QUESTION_PRIORITY;j>=0;j--){
                    if(!allQuestionsByPriority[j].isEmpty()||allQuestionsByPriority[j]==null){
                        numberOfPartitions+=partitionsPerLevel;
                        partitionsPerLevel*=2;
                    }
                }

                rangePerPartition = (int)Math.ceil(PROBABILITY_GENERATOR_RANGE*1.0/numberOfPartitions);
                partitionsPerLevel = 1;

                //initialize ranges
                for(int j=QuestionPriorityService.MAX_QUESTION_PRIORITY;j>=0;j--){
                    if(!allQuestionsByPriority[j].isEmpty()){
                        priorityRangeList.addRange(
                                previousBound + 1,
                                previousBound + partitionsPerLevel*rangePerPartition,
                                j
                                );
                        previousBound = previousBound + partitionsPerLevel * rangePerPartition;
                        partitionsPerLevel *= 2;

                    }
                }

                addToRandomQuestions(rand,
                        allQuestionsByPriority[priorityRangeList.findPriorityOfNumber(generatedRandomNumber)],
                        randomQuestions,
                        1);
            }
        }
        return randomQuestions;
    }

    public void addToRandomQuestions(Random rand, List<QuestionDto> allQuestions, List<QuestionDto> randomQuestions, int numberOfQuestions) {

        try{
            for (int i = 0; i < numberOfQuestions; i++) {
                int randomIndex = rand.nextInt(allQuestions.size());
                QuestionDto questionDto = allQuestions.get(randomIndex);
                questionDto.randomlyOrderAnswers();
                randomQuestions.add(questionDto);
                allQuestions.remove(randomIndex);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public QuestionDto getById(int questionId) {
        ModelMapper modelMapper = new ModelMapper();
        QuestionDto questionDto = modelMapper.map(questionRepository.findById(questionId).orElseThrow(
                ()->new IllegalArgumentException("Question not found")
        ),QuestionDto.class);
        return questionDto;
    }

}
