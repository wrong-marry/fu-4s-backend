INSERT INTO `swp_project_summer_2024`.`user`
(`username`, `email`, `first_name`, `last_name`, `password`, `role`, `status`)
VALUES
('user1', 'user1@example.com', 'John', 'Doe', 'password123', 'USER', 'ACTIVE'),
('user2', 'user2@example.com', 'Jane', 'Doe', 'password123', 'USER', 'ACTIVE'),
('user3', 'user3@example.com', 'Mike', 'Smith', 'password123', 'STAFF', 'ACTIVE'),
('user4', 'user4@example.com', 'Mary', 'Johnson', 'password123', 'STAFF', 'ACTIVE'),
('user5', 'user5@example.com', 'Chris', 'Brown', 'password123', 'ADMIN', 'ACTIVE'),
('user6', 'user6@example.com', 'Pat', 'Williams', 'password123', 'USER', 'BANNED'),
('user7', 'user7@example.com', 'Alex', 'Jones', 'password123', 'USER', 'ACTIVE'),
('user8', 'user8@example.com', 'Sam', 'Garcia', 'password123', 'STAFF', 'BANNED'),
('user9', 'user9@example.com', 'Taylor', 'Martinez', 'password123', 'ADMIN', 'ACTIVE'),
('user10', 'user10@example.com', 'Jordan', 'Rodriguez', 'password123', 'USER', 'ACTIVE');




INSERT INTO `swp_project_summer_2024`.`subject`
(`code`, `name`, `semester`)
VALUES
('SSL101c', 'Academic Skills for University Success', 1),
('CSI104', 'Introduction to Computer Science', 1),
('PRF192', 'Programming Fundamentals', 1), 
('MAE101', 'Mathematics for Engineering', 1),
('CEA201', 'Computer Organization and Architecture', 1),
('PRO192', 'Object-Oriented Programming', 2),
('MAD101', 'Discrete mathematics', 2),
('OSG202', 'Operating Systems', 2),
('NWC203c', 'Computer Networking', 2),
('SSG104', 'Communication and In-Group Working Skills', 2),
('JPD113', 'Elementary Japanese 1-A1.1', 3),
('CSD201', 'Data Structures and Algorithms', 3),
('DBI202', 'Introduction to Databases', 3),
('LAB211', 'OOP with Java Lab', 3),
('WED201c', 'Web Design', 3),
('MAS291', 'Statistics and Probability', 4),
('JPD123', 'Elementary Japanese 1-A1.2', 4),
('IOT102', 'Internet vạn vật', 4),
('PRJ301', 'Java Web Application Development', 4),
('SWE201c', 'Introduction to Software Engineering', 4),
('SWP391', 'Application development project', 5),
('ITE302c', 'Ethics in IT', 5),
('ACC101', 'Principles of Accounting', 5),
('SWR302', 'Software Requirement', 5),
('SWT301', 'Software Testing', 5),
('OJT202', 'On the job training', 6),
('ENW492c', 'Writing Research Papers', 6),
('SE-0003', 'Elective 3', 7),
('PRM392', 'Mobile Programming', 7),
('SWD392', 'SW Architecture and Design', 7),
('EXE101', 'Experiential Entrepreneurship 1', 7),
('SE-0002', 'Elective 2', 7),
('MLN122', 'Political economics of Marxism – Leninism', 8),
('MLN111', 'Philosophy of Marxism – Leninism', 8),
('SAP341', 'SAP Application Development with ABAP', 8),
('EXE201', 'Experiential Entrepreneurship 2', 8),
('PMG202c', 'Project management', 8),
('WDU203c', 'UI/UX Design', 8),
('MLN131', 'Scientific socialism', 9),
('VNR202', 'History of Việt Nam Communist Party', 9),
('SEP490', 'SE Capstone Project', 9),
('HCM202', 'Ho Chi Minh Ideology', 9);





INSERT INTO `swp_project_summer_2024`.`notification`
(`is_seen`, `message`, `time`, `username`)
VALUES
(0, 'APPROVED_POST', '2024-05-20 08:00:00.000000', 'user1'),
(0, 'DISAPPROVED_POST', '2024-05-20 09:00:00.000000', 'user2'),
(1, 'HIDE_COMMENT', '2024-05-19 07:30:00.000000', 'user3'),
(0, 'APPROVED_POST', '2024-05-18 10:15:00.000000', 'user4'),
(1, 'DISAPPROVED_POST', '2024-05-17 11:45:00.000000', 'user5'),
(0, 'HIDE_COMMENT', '2024-05-16 12:00:00.000000', 'user6'),
(1, 'APPROVED_POST', '2024-05-15 13:30:00.000000', 'user7'),
(0, 'DISAPPROVED_POST', '2024-05-14 14:00:00.000000', 'user8'),
(1, 'HIDE_COMMENT', '2024-05-13 15:45:00.000000', 'user9'),
(0, 'APPROVED_POST', '2024-05-12 16:30:00.000000', 'user10');




INSERT INTO `swp_project_summer_2024`.`post`
(`is_test`, `post_time`, `status`, `title`, `subject_code`, `username`)
VALUES
(0, '2023-05-21 10:30:00', 'ACTIVE', 'Giới thiệu về môn Lập trình Web', 'SWP391', 'user3'),
(0, '2023-05-22 14:45:00', 'ACTIVE', 'Hướng dẫn sử dụng Git cho dự án', 'SWP391', 'user1'),
(0, '2023-05-23 09:15:00', 'PENDING_APPROVE', 'Cấu trúc thư mục và tệp tin trong dự án Web', 'SWP391', 'user2'),
(0, '2023-05-24 16:20:00', 'PENDING_APPROVE', 'Cơ bản về HTML và CSS', 'SWP391', 'user3'),
(0, '2023-05-25 11:00:00', 'PENDING_APPROVE', 'Sử dụng Bootstrap để tạo giao diện responsive', 'SWP391', 'user1'),
(0, '2023-05-26 13:35:00', 'ACTIVE', 'Giới thiệu về JavaScript và jQuery', 'SWP391', 'user2'),
(0, '2023-05-27 08:45:00', 'ACTIVE', 'Xây dựng tính năng CRUD với PHP', 'SWP391', 'user3'),
(0, '2023-05-28 15:10:00', 'ACTIVE', 'Thiết kế cơ sở dữ liệu cho dự án Web', 'SWP391', 'user1'),
(0, '2023-05-29 10:50:00', 'ACTIVE', 'Tối ưu hóa tốc độ website với PHP và MySQL', 'SWP391', 'user2'),
(0, '2023-05-30 14:00:00', 'ACTIVE', 'Triển khai dự án Web lên hosting', 'SWP391', 'user3'),
(0, '2023-05-31 09:25:00', 'ACTIVE', 'Bảo mật website với PHP và MySQL', 'SWP391', 'user1'),
(0, '2023-06-01 16:40:00', 'ACTIVE', 'Sử dụng Ajax để cải thiện trải nghiệm người dùng', 'SWP391', 'user2'),
(0, '2023-06-02 11:15:00', 'ACTIVE', 'Tích hợp API trong dự án Web', 'SWP391', 'user3'),
(0, '2023-06-03 13:55:00', 'ACTIVE', 'Tạo giao diện quản trị admin với PHP', 'SWP391', 'user1'),
(0, '2023-06-04 08:30:00', 'ACTIVE', 'Xử lý lỗi và debug code PHP', 'SWP391', 'user2'),
(0, '2023-06-05 15:20:00', 'ACTIVE', 'Sử dụng Laravel framework để xây dựng dự án Web', 'SWP391', 'user3'),
(0, '2023-06-06 10:40:00', 'HIDDEN', 'Quản lý phiên bản code với Git và GitHub', 'SWP391', 'user1'),
(0, '2023-06-07 14:05:00', 'HIDDEN', 'Tích hợp thanh toán trực tuyến trong website', 'SWP391', 'user2'),
(0, '2023-06-08 09:50:00', 'HIDDEN', 'Cải thiện SEO cho dự án Web', 'SWP391', 'user3'),
(0, '2023-06-09 16:15:00', 'ACTIVE', 'Triển khai ứng dụng web với Docker', 'SWP391', 'user1'),
(0, '2023-05-22 15:30:00', 'ACTIVE', 'Hướng dẫn thiết kế database cho dự án', 'DBI202', 'user5'),
(0, '2023-05-23 10:00:00', 'ACTIVE', 'Thực hành truy vấn SQL cơ bản', 'DBI202', 'user6'),
(0, '2023-05-21 13:20:00', 'ACTIVE', 'Giới thiệu về môn Lập trình hướng đối tượng', 'PRO192', 'user7'),
(0, '2023-05-22 16:45:00', 'ACTIVE', 'Hướng dẫn sử dụng các thiết kế mẫu trong OOP', 'PRO192', 'user8'),
(0, '2023-05-23 11:30:00', 'ACTIVE', 'Thực hành xây dựng ứng dụng OOP đơn giản', 'PRO192', 'user9'),
(0, '2023-05-21 14:00:00', 'ACTIVE', 'Giới thiệu về môn Toán rời rạc', 'MAD101', 'user1'),
(0, '2023-05-22 17:15:00', 'ACTIVE', 'Hướng dẫn giải các bài toán về logic học', 'MAD101', 'user4'),
(0, '2023-05-23 13:45:00', 'ACTIVE', 'Ứng dụng của Toán rời rạc trong khoa học máy tính', 'MAD101', 'user5'),


(1, '2023-05-24 09:00:00', 'ACTIVE', 'Tài liệu ôn tập môn Cơ sở Dữ liệu', 'DBI202', 'user3'),
(1, '2023-05-25 14:00:00', 'ACTIVE', 'Những câu hỏi quan trọng cần nắm chắc cho Kỳ thi Cuối kỳ môn Cơ sở Dữ liệu', 'DBI202', 'user4'),
(1, '2023-05-26 10:30:00', 'HIDDEN', 'Làm quen với các bài tập Phần 1 môn Cơ sở Dữ liệu', 'DBI202', 'user5'),
(1, '2023-05-25 15:30:00', 'HIDDEN', 'Những khái niệm cơ bản nhất cần nắm vững trong môn Lập trình Hướng đối tượng', 'PRO192', 'user7'),
(1, '2023-05-26 13:00:00', 'ACTIVE', 'Cùng làm các bài tập Phần 1 môn Lập trình Hướng đối tượng', 'PRO192', 'user8'),
(1, '2023-05-25 16:45:00', 'ACTIVE', 'Ôn tập lại những kiến thức chính cho Kỳ thi Cuối kỳ môn Toán rời rạc', 'MAD101', 'user2'),
(1, '2023-05-26 14:15:00', 'ACTIVE', 'Cùng thực hành các bài tập Phần 1 môn Toán rời rạc', 'MAD101', 'user2'),
(1, '2023-05-25 14:00:00', 'ACTIVE', 'Những câu hỏi quan trọng cần nắm chắc cho Kỳ thi Cuối kỳ môn Cơ sở Dữ liệu', 'DBI202', 'user4'),
(1, '2023-05-26 10:30:00', 'PENDING_APPROVE', 'Làm quen với các bài tập Phần 1 môn Cơ sở Dữ liệu', 'DBI202', 'user5'),
(1, '2023-05-27 09:00:00', 'PENDING_APPROVE', '100 câu hỏi thông dụng nhất về môn Cơ sở Dữ liệu', 'DBI202', 'user2'),
(1, '2023-05-28 14:00:00', 'PENDING_APPROVE', '300 câu cơ bản cần nắm vững cho môn Cơ sở Dữ liệu', 'DBI202', 'user3'),
(1, '2023-05-25 15:30:00', 'ACTIVE', 'Ôn tập lại những kiến thức chính cho Kỳ thi Cuối kỳ môn Lập trình Hướng đối tượng', 'PRO192', 'user7'),
(1, '2023-05-26 13:00:00', 'ACTIVE', 'Cùng làm các bài tập Phần 1 môn Lập trình Hướng đối tượng', 'PRO192', 'user8'),
(1, '2023-05-27 11:15:00', 'ACTIVE', '150 câu hỏi hay nhất về môn Lập trình Hướng đối tượng', 'PRO192', 'user4'),
(1, '2023-05-28 15:30:00', 'ACTIVE', '500 câu cơ bản cần nắm vững cho môn Lập trình Hướng đối tượng', 'PRO192', 'user5'),
(1, '2023-05-24 13:45:00', 'ACTIVE', 'Tài liệu ôn tập môn Toán rời rạc', 'MAD101', 'user9'),
(1, '2023-05-25 16:45:00', 'ACTIVE', 'Ôn tập lại những kiến thức chính cho Kỳ thi Cuối kỳ môn Toán rời rạc', 'MAD101', 'user2'),
(1, '2023-05-27 13:45:00', 'ACTIVE', '120 câu hỏi quan trọng về môn CSD201', 'CSD201', 'user6'),
(1, '2023-05-28 16:45:00', 'ACTIVE', '400 câu cơ bản cần nắm vững cho môn Toán rời rạc', 'MAD101', 'user7');



INSERT INTO `swp_project_summer_2024`.`question_set`
(`attempts`, `id`)
VALUES
(3, 1),
(5, 2),
(2, 3),
(6, 4),
(1, 5),
(4, 6),
(7, 7),
(3, 8),
(2, 9),
(5, 10);

INSERT INTO `swp_project_summer_2024`.`test_result`
(`id`, `date`, `result`, `question_set_id`, `username`)
VALUES
(1, '2024-05-22 08:00:00.000000', 8, 1, 'user1'),
(2, '2024-05-22 09:00:00.000000', 7, 2, 'user2'),
(3, '2024-05-22 10:00:00.000000', 9, 3, 'user3'),
(4, '2024-05-22 11:00:00.000000', 6, 4, 'user4'),
(5, '2024-05-22 12:00:00.000000', 7, 5, 'user5'),
(6, '2024-05-22 13:00:00.000000', 8, 6, 'user6'),
(7, '2024-05-22 14:00:00.000000', 9, 7, 'user7'),
(8, '2024-05-22 15:00:00.000000', 4, 8, 'user8'),
(9, '2024-05-22 16:00:00.000000', 8, 9, 'user9'),
(10, '2024-05-22 17:00:00.000000', 7, 10, 'user10');


INSERT INTO `swp_project_summer_2024`.`question`
(`id`, `content`, `question_set_id`)
VALUES
(1, 'What is the capital of France?', 1),
(2, 'Who wrote the novel "To Kill a Mockingbird"?', 1),
(3, 'What is the chemical symbol for gold?', 1),
(4, 'What year did World War II end?', 1),
(5, 'What is the formula for the area of a rectangle?', 2),
(6, 'What is the Pythagorean theorem?', 2),
(7, 'What is the process of cell division called?', 2),
(8, 'Who discovered penicillin?', 2),
(9, 'What is the first element on the periodic table?', 3),
(10, 'What is the largest planet in our solar system?', 3);

INSERT INTO `swp_project_summer_2024`.`answer`
(`id`, `content`, `is_correct`, `question_id`)
VALUES
(1, 'Paris', 1, 1),
(2, 'London', 0, 1),
(3, 'Rome', 0, 1),
(4, 'Berlin', 0, 1),
(5, 'Harper Lee', 1, 2),
(6, 'Mark Twain', 0, 2),
(7, 'J.K. Rowling', 0, 2),
(8, 'Charles Dickens', 0, 2)
;


INSERT INTO swp_project_summer_2024.learning_material
(content, id)
VALUES
('Lập trình web bao gồm HTML, CSS, JavaScript. Các công nghệ này hoạt động cùng nhau để tạo ra các trang web động và tương tác.', 1),
('Git là hệ thống quản lý phiên bản phân tán, giúp quản lý mã nguồn và cộng tác với các lập trình viên khác.', 2),
('Cấu trúc thư mục và tệp tin là cơ sở quan trọng khi xây dựng dự án web. Việc tổ chức các tệp và thư mục một cách logic và hiệu quả là rất cần thiết.', 3),
('HTML cung cấp các thẻ cơ bản, CSS được sử dụng để định dạng và tạo kiểu cho các phần tử, bao gồm layout và responsive design.', 4),
('Bootstrap là framework CSS phổ biến giúp xây dựng giao diện web responsive một cách nhanh chóng, tạo ra các trang web đẹp mắt và thân thiện với người dùng.', 5),
('JavaScript là ngôn ngữ lập trình client-side, jQuery là thư viện JavaScript phổ biến, chúng có thể được sử dụng để tạo ra các trang web tương tác và động.', 6),
('PHP là ngôn ngữ lập trình server-side, kết hợp với MySQL để quản lý dữ liệu của ứng dụng web, cung cấp các chức năng CRUD.', 7),
('Thiết kế cơ sở dữ liệu là bước quan trọng khi xây dựng ứng dụng web. Việc thiết kế cơ sở dữ liệu hiệu quả và phù hợp với yêu cầu dự án là rất cần thiết.', 8),
('Tối ưu hóa tốc độ website bao gồm tối ưu hóa cơ sở dữ liệu MySQL và các kỹ thuật PHP khác, để đảm bảo website hoạt động nhanh chóng và hiệu quả.', 9),
('Khi hoàn thành dự án web, cần triển khai nó lên hosting. Các bước cần thiết để triển khai dự án web lên hosting một cách thành công.', 10);




INSERT INTO `swp_project_summer_2024`.`learning_material`
(`id`, `content`)
VALUES
(11, 'Tối ưu hóa tốc độ website với PHP và MySQL'),
(12, 'Triển khai dự án Web lên hosting'),
(13, 'Bảo mật website với PHP và MySQL'),
(14, 'Sử dụng Ajax để cải thiện trải nghiệm người dùng'),
(15, 'Tích hợp API trong dự án Web'),
(16, 'Tạo giao diện quản trị admin với PHP'),
(17, 'Xử lý lỗi và debug code PHP'),
(18, 'Sử dụng Laravel framework để xây dựng dự án Web'),
(19, 'Quản lý phiên bản code với Git và GitHub'),
(20, 'Tích hợp thanh toán trực tuyến trong website'),
(21, 'Cải thiện SEO cho dự án Web'),
(22, 'Triển khai ứng dụng web với Docker'),
(23, 'Hướng dẫn thiết kế database cho dự án'),
(24, 'Thực hành truy vấn SQL cơ bản'),
(25, 'Giới thiệu về môn Lập trình hướng đối tượng'),
(26, 'Hướng dẫn sử dụng các thiết kế mẫu trong OOP'),
(27, 'Thực hành xây dựng ứng dụng OOP đơn giản'),
(28, 'Giới thiệu về môn Toán rời rạc');


INSERT INTO `swp_project_summer_2024`.`material_image`
(`id`, `filename`, `material_id`)
VALUES
(1, 'html-css-js.jpg', 1),
(2, 'git-logo.png', 2),
(3, 'directory-structure.jpg', 3),
(4, 'html-css-demo.png', 4),
(5, 'bootstrap-example.jpg', 5),
(6, 'javascript-jquery.jpg', 6),
(7, 'php-mysql.png', 7),
(8, 'database-design.jpg', 8),
(9, 'website-optimization.png', 9),
(10, 'web-deployment.jpg', 10);


INSERT INTO `swp_project_summer_2024`.`comment`
(`id`, `content`, `date`, `status`, `post_id`, `username`)
VALUES
(1, 'Bài hay quá ạ!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user1'),
(2, 'Tôi không đồng ý hoàn toàn với quan điểm này.', '2024-05-16 14:20:00', 'ACTIVE', 15, 'user2'),
(3, 'Tuyệt vời, bạn đã giải quyết vấn đề rất tốt!', '2024-05-17 09:45:00', 'ACTIVE', 15, 'user3'),
(4, 'Bài này cần thêm thông tin chi tiết hơn.', '2024-05-18 16:10:00', 'HIDDEN', 5, 'user4'),
(5, 'Tôi thích cách bạn trình bày ý tưởng, rất logic.', '2024-05-19 11:00:00', 'ACTIVE', 5, 'user5'),
(6, 'Cảm ơn bạn đã chia sẻ kinh nghiệm ^^.', '2024-05-20 13:40:00', 'ACTIVE', 5, 'user6'),
(7, 'Bài viết này cần sửa lỗi chính tả, mong bạn kiểm tra lại.', '2024-05-21 08:50:00', 'HIDDEN', 3, 'user7'),
(8, 'Tôi hoàn toàn đồng ý với quan điểm của bạn, rất thuyết phục.', '2024-05-22 15:30:00', 'ACTIVE', 3, 'user8'),
(9, 'Bài viết này rất hấp dẫn, tôi thích nó lắm!', '2024-05-23 10:00:00', 'ACTIVE', 11, 'user9'),
(10, 'Bạn ơi, có thể bổ sung thêm thông tin về chủ đề này được không ạ?', '2024-05-24 14:15:00', 'HIDDEN', 11, 'user10');


