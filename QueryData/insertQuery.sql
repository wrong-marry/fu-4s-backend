INSERT INTO `swp_project_summer_2024`.`user`
(`username`, `email`, `first_name`, `last_name`, `password`, `role`, `status`)
VALUES
    ('user01', 'user1@example.com', 'John', 'Doe', '$2a$12$cIt2HzBknwqN1SKCxbn2ouRcBflU7A2sfFm9DfwyVa6OI0Ty80cgC', 'USER', 'ACTIVE'),
    ('user02', 'user2@example.com', 'Jane', 'Doe', '$2a$12$cIt2HzBknwqN1SKCxbn2ouRcBflU7A2sfFm9DfwyVa6OI0Ty80cgC', 'USER', 'ACTIVE'),
    ('user03', 'user3@example.com', 'Mike', 'Smith', '$2a$12$cIt2HzBknwqN1SKCxbn2ouRcBflU7A2sfFm9DfwyVa6OI0Ty80cgC', 'STAFF', 'ACTIVE'),
    ('user04', 'user4@example.com', 'Mary', 'Johnson', '$2a$12$cIt2HzBknwqN1SKCxbn2ouRcBflU7A2sfFm9DfwyVa6OI0Ty80cgC', 'STAFF', 'ACTIVE'),
    ('user05', 'user5@example.com', 'Chris', 'Brown', '$2a$12$cIt2HzBknwqN1SKCxbn2ouRcBflU7A2sfFm9DfwyVa6OI0Ty80cgC', 'ADMIN', 'ACTIVE'),
    ('user06', 'user6@example.com', 'Pat', 'Williams', '$2a$12$cIt2HzBknwqN1SKCxbn2ouRcBflU7A2sfFm9DfwyVa6OI0Ty80cgC', 'USER', 'BANNED'),
    ('user07', 'user7@example.com', 'Alex', 'Jones', '$2a$12$cIt2HzBknwqN1SKCxbn2ouRcBflU7A2sfFm9DfwyVa6OI0Ty80cgC', 'USER', 'ACTIVE'),
    ('user08', 'user8@example.com', 'Sam', 'Garcia', '$2a$12$cIt2HzBknwqN1SKCxbn2ouRcBflU7A2sfFm9DfwyVa6OI0Ty80cgC', 'STAFF', 'BANNED'),
    ('user09', 'user9@example.com', 'Taylor', 'Martinez', '$2a$12$cIt2HzBknwqN1SKCxbn2ouRcBflU7A2sfFm9DfwyVa6OI0Ty80cgC', 'ADMIN', 'ACTIVE'),
    ('user10', 'user10@example.com', 'Jordan', 'Rodriguez', '$2a$12$cIt2HzBknwqN1SKCxbn2ouRcBflU7A2sfFm9DfwyVa6OI0Ty80cgC', 'USER', 'ACTIVE');




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
    (0, 'APPROVED_POST', '2024-05-20 08:00:00.000000', 'user01'),
    (1, 'APPROVED_POST', '2024-05-21 12:00:00.000000', 'user01'),
    (1, 'DISAPPROVED_POST', '2024-05-21 13:00:00.000000', 'user01'),
    (0, 'HIDE_COMMENT', '2024-05-21 14:00:00.000000', 'user01'),
    (0, 'APPROVED_POST', '2024-05-21 15:00:00.000000', 'user01'),
    (0, 'DISAPPROVED_POST', '2024-05-21 16:00:00.000000', 'user01'),
    (0, 'HIDE_COMMENT', '2024-05-21 17:00:00.000000', 'user01'),
    (0, 'APPROVED_POST', '2024-05-21 18:00:00.000000', 'user01'),
    (0, 'DISAPPROVED_POST', '2024-05-21 19:00:00.000000', 'user01'),
    (0, 'HIDE_COMMENT', '2024-05-21 20:00:00.000000', 'user01'),
    (0, 'APPROVED_POST', '2024-05-21 21:00:00.000000', 'user01'),
    (0, 'DISAPPROVED_POST', '2024-05-20 09:00:00.000000', 'user02'),
    (1, 'HIDE_COMMENT', '2024-05-19 07:30:00.000000', 'user03'),
    (0, 'APPROVED_POST', '2024-05-18 10:15:00.000000', 'user04'),
    (1, 'DISAPPROVED_POST', '2024-05-17 11:45:00.000000', 'user05'),
    (0, 'HIDE_COMMENT', '2024-05-16 12:00:00.000000', 'user06'),
    (1, 'APPROVED_POST', '2024-05-15 13:30:00.000000', 'user07'),
    (0, 'DISAPPROVED_POST', '2024-05-14 14:00:00.000000', 'user08'),
    (1, 'HIDE_COMMENT', '2024-05-13 15:45:00.000000', 'user09'),
    (0, 'APPROVED_POST', '2024-05-12 16:30:00.000000', 'user10');




INSERT INTO `swp_project_summer_2024`.`post`
(`is_test`, `post_time`, `status`, `title`, `subject_code`, `username`)
VALUES
    (0, '2023-05-21 10:30:00', 'ACTIVE', 'Giới thiệu về môn Lập trình Web', 'SWP391', 'user03'),
    (0, '2023-05-22 14:45:00', 'ACTIVE', 'Hướng dẫn sử dụng Git cho dự án', 'SWP391', 'user01'),
    (0, '2023-05-23 09:15:00', 'PENDING_APPROVE', 'Cấu trúc thư mục và tệp tin trong dự án Web', 'SWP391', 'user02'),
    (0, '2023-05-24 16:20:00', 'PENDING_APPROVE', 'Cơ bản về HTML và CSS', 'SWP391', 'user03'),
    (0, '2023-05-25 11:00:00', 'PENDING_APPROVE', 'Sử dụng Bootstrap để tạo giao diện responsive', 'SWP391', 'user01'),
    (0, '2023-05-26 13:35:00', 'ACTIVE', 'Giới thiệu về JavaScript và jQuery', 'SWP391', 'user02'),
    (0, '2023-05-27 08:45:00', 'ACTIVE', 'Xây dựng tính năng CRUD với PHP', 'SWP391', 'user03'),
    (0, '2023-05-28 15:10:00', 'ACTIVE', 'Thiết kế cơ sở dữ liệu cho dự án Web', 'SWP391', 'user01'),
    (0, '2023-05-29 10:50:00', 'ACTIVE', 'Tối ưu hóa tốc độ website với PHP và MySQL', 'SWP391', 'user02'),
    (0, '2023-05-30 14:00:00', 'ACTIVE', 'Triển khai dự án Web lên hosting', 'SWP391', 'user03'),
    (0, '2023-05-31 09:25:00', 'ACTIVE', 'Bảo mật website với PHP và MySQL', 'SWP391', 'user01'),
    (0, '2023-06-01 16:40:00', 'ACTIVE', 'Sử dụng Ajax để cải thiện trải nghiệm người dùng', 'SWP391', 'user02'),
    (0, '2023-06-02 11:15:00', 'ACTIVE', 'Tích hợp API trong dự án Web', 'SWP391', 'user03'),
    (0, '2023-06-03 13:55:00', 'ACTIVE', 'Tạo giao diện quản trị admin với PHP', 'SWP391', 'user01'),
    (0, '2023-06-04 08:30:00', 'ACTIVE', 'Xử lý lỗi và debug code PHP', 'SWP391', 'user02'),
    (0, '2023-06-05 15:20:00', 'ACTIVE', 'Sử dụng Laravel framework để xây dựng dự án Web', 'SWP391', 'user03'),
    (0, '2023-06-06 10:40:00', 'HIDDEN', 'Quản lý phiên bản code với Git và GitHub', 'SWP391', 'user01'),
    (0, '2023-06-07 14:05:00', 'HIDDEN', 'Tích hợp thanh toán trực tuyến trong website', 'SWP391', 'user02'),
    (0, '2023-06-08 09:50:00', 'HIDDEN', 'Cải thiện SEO cho dự án Web', 'SWP391', 'user03'),
    (0, '2023-06-09 16:15:00', 'ACTIVE', 'Triển khai ứng dụng web với Docker', 'SWP391', 'user01'),
    (0, '2023-05-22 15:30:00', 'ACTIVE', 'Hướng dẫn thiết kế database cho dự án', 'DBI202', 'user05'),
    (0, '2023-05-23 10:00:00', 'ACTIVE', 'Thực hành truy vấn SQL cơ bản', 'DBI202', 'user06'),
    (0, '2023-05-21 13:20:00', 'ACTIVE', 'Giới thiệu về môn Lập trình hướng đối tượng', 'PRO192', 'user07'),
    (0, '2023-05-22 16:45:00', 'ACTIVE', 'Hướng dẫn sử dụng các thiết kế mẫu trong OOP', 'PRO192', 'user08'),
    (0, '2023-05-23 11:30:00', 'ACTIVE', 'Thực hành xây dựng ứng dụng OOP đơn giản', 'PRO192', 'user09'),
    (0, '2023-05-21 14:00:00', 'ACTIVE', 'Giới thiệu về môn Toán rời rạc', 'MAD101', 'user01'),
    (0, '2023-05-22 17:15:00', 'ACTIVE', 'Hướng dẫn giải các bài toán về logic học', 'MAD101', 'user04'),
    (0, '2023-05-23 13:45:00', 'ACTIVE', 'Ứng dụng của Toán rời rạc trong khoa học máy tính', 'MAD101', 'user05'),


    (1, '2023-05-24 09:00:00', 'ACTIVE', 'Tài liệu ôn tập môn Cơ sở Dữ liệu', 'DBI202', 'user03'),
    (1, '2023-05-25 14:00:00', 'ACTIVE', 'Những câu hỏi quan trọng cần nắm chắc cho Kỳ thi Cuối kỳ môn Cơ sở Dữ liệu', 'DBI202', 'user04'),
    (1, '2023-05-26 10:30:00', 'HIDDEN', 'Làm quen với các bài tập Phần 1 môn Cơ sở Dữ liệu', 'DBI202', 'user05'),
    (1, '2023-05-25 15:30:00', 'HIDDEN', 'Những khái niệm cơ bản nhất cần nắm vững trong môn Lập trình Hướng đối tượng', 'PRO192', 'user07'),
    (1, '2023-05-26 13:00:00', 'ACTIVE', 'Cùng làm các bài tập Phần 1 môn Lập trình Hướng đối tượng', 'PRO192', 'user08'),
    (1, '2023-05-25 16:45:00', 'ACTIVE', 'Ôn tập lại những kiến thức chính cho Kỳ thi Cuối kỳ môn Toán rời rạc', 'MAD101', 'user02'),
    (1, '2023-05-26 14:15:00', 'ACTIVE', 'Cùng thực hành các bài tập Phần 1 môn Toán rời rạc', 'MAD101', 'user02'),
    (1, '2023-05-25 14:00:00', 'ACTIVE', 'Những câu hỏi quan trọng cần nắm chắc cho Kỳ thi Cuối kỳ môn Cơ sở Dữ liệu', 'DBI202', 'user04'),
    (1, '2023-05-26 10:30:00', 'PENDING_APPROVE', 'Làm quen với các bài tập Phần 1 môn Cơ sở Dữ liệu', 'DBI202', 'user05'),
    (1, '2023-05-27 09:00:00', 'PENDING_APPROVE', '100 câu hỏi thông dụng nhất về môn Cơ sở Dữ liệu', 'DBI202', 'user02'),
    (1, '2023-05-28 14:00:00', 'PENDING_APPROVE', '300 câu cơ bản cần nắm vững cho môn Cơ sở Dữ liệu', 'DBI202', 'user03'),
    (1, '2023-05-25 15:30:00', 'ACTIVE', 'Ôn tập lại những kiến thức chính cho Kỳ thi Cuối kỳ môn Lập trình Hướng đối tượng', 'PRO192', 'user07'),
    (1, '2023-05-26 13:00:00', 'ACTIVE', 'Cùng làm các bài tập Phần 1 môn Lập trình Hướng đối tượng', 'PRO192', 'user08'),
    (1, '2023-05-27 11:15:00', 'ACTIVE', '150 câu hỏi hay nhất về môn Lập trình Hướng đối tượng', 'PRO192', 'user04'),
    (1, '2023-05-28 15:30:00', 'ACTIVE', '500 câu cơ bản cần nắm vững cho môn Lập trình Hướng đối tượng', 'PRO192', 'user05'),
    (1, '2023-05-24 13:45:00', 'ACTIVE', 'Tài liệu ôn tập môn Toán rời rạc', 'MAD101', 'user09'),
    (1, '2023-05-25 16:45:00', 'ACTIVE', 'Ôn tập lại những kiến thức chính cho Kỳ thi Cuối kỳ môn Toán rời rạc', 'MAD101', 'user02'),
    (1, '2023-05-27 13:45:00', 'ACTIVE', '120 câu hỏi quan trọng về môn CSD201', 'CSD201', 'user06'),
    (1, '2023-05-28 16:45:00', 'ACTIVE', '400 câu cơ bản cần nắm vững cho môn Toán rời rạc', 'MAD101', 'user07');



INSERT INTO `swp_project_summer_2024`.`question_set`
(`attempts`, `id`)
VALUES
    (3, 29),
    (5, 30),
    (2, 31),
    (6, 32),
    (1, 33),
    (4, 34),
    (7, 35),
    (3, 36),
    (2, 37),
    (5, 38),
    (3, 39),
    (5, 40),
    (2, 41),
    (6, 42),
    (1, 43),
    (4, 44),
    (7, 45),
    (3, 46),
    (2, 47);


INSERT INTO `swp_project_summer_2024`.`test_result`
(`id`, `date`, `result`, `question_set_id`, `username`)
VALUES
    (11, '2024-05-22 18:00:00.000000', 8, 29, 'user01'),
    (12, '2024-05-22 19:00:00.000000', 7, 30, 'user02'),
    (13, '2024-05-22 20:00:00.000000', 9, 31, 'user03'),
    (14, '2024-05-22 21:00:00.000000', 6, 32, 'user04'),
    (15, '2024-05-22 22:00:00.000000', 7, 33, 'user05'),
    (16, '2024-05-22 23:00:00.000000', 8, 34, 'user06'),
    (17, '2024-05-23 00:00:00.000000', 9, 35, 'user07'),
    (18, '2024-05-23 01:00:00.000000', 4, 36, 'user08'),
    (19, '2024-05-23 02:00:00.000000', 8, 37, 'user09'),
    (20, '2024-05-23 03:00:00.000000', 7, 38, 'user10'),
    (21, '2024-05-23 04:00:00.000000', 8, 39, 'user01'),
    (22, '2024-05-23 05:00:00.000000', 7, 40, 'user02'),
    (23, '2024-05-23 06:00:00.000000', 9, 41, 'user03'),
    (24, '2024-05-23 07:00:00.000000', 6, 42, 'user04'),
    (25, '2024-05-23 08:00:00.000000', 7, 43, 'user05'),
    (26, '2024-05-23 09:00:00.000000', 8, 44, 'user06'),
    (27, '2024-05-23 10:00:00.000000', 9, 45, 'user07'),
    (28, '2024-05-23 11:00:00.000000', 4, 46, 'user08'),
    (29, '2024-05-23 12:00:00.000000', 8, 47, 'user09');


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
    (1, 'Bài hay quá ạ!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user01'),
    (2, 'Tôi không đồng ý hoàn toàn với quan điểm này.', '2024-05-16 14:20:00', 'ACTIVE', 15, 'user04'),
    (3, 'Tuyệt vời, bạn đã giải quyết vấn đề rất tốt!', '2024-05-17 09:45:00', 'ACTIVE', 15, 'user03'),
    (4, 'Bài này cần thêm thông tin chi tiết hơn.', '2024-05-18 16:10:00', 'HIDDEN', 5, 'user04'),
    (5, 'Tôi thích cách bạn trình bày ý tưởng, rất logic.', '2024-05-19 11:00:00', 'ACTIVE', 5, 'user05'),
    (6, 'Cảm ơn bạn đã chia sẻ kinh nghiệm ^^.', '2024-05-20 13:40:00', 'ACTIVE', 5, 'user06'),
    (7, 'Bài viết này cần sửa lỗi chính tả, mong bạn kiểm tra lại.', '2024-05-21 08:50:00', 'HIDDEN', 3, 'user07'),
    (8, 'Tôi hoàn toàn đồng ý với quan điểm của bạn, rất thuyết phục.', '2024-05-22 15:30:00', 'ACTIVE', 3, 'user08'),
    (9, 'Bài viết này rất hấp dẫn, tôi thích nó lắm!', '2024-05-23 10:00:00', 'ACTIVE', 11, 'user09'),
    (10, 'Bạn ơi, có thể bổ sung thêm thông tin về chủ đề này được không ạ?', '2024-05-24 14:15:00', 'HIDDEN', 11, 'user10');


INSERT INTO `swp_project_summer_2024`.`question`
(`id`, `content`, `question_set_id`)
VALUES
    (1, 'What is the capital of France?', 29),
    (2, 'Who wrote the novel "To Kill a Mockingbird"?', 29),
    (3, 'What is the chemical symbol for gold?', 29),
    (4, 'What year did World War II end?', 29),
    (5, 'What is the formula for the area of a rectangle?', 31),
    (6, 'What is the Pythagorean theorem?', 31),
    (7, 'What is the process of cell division called?', 31),
    (8, 'Who discovered penicillin?', 31),
    (9, 'What is the first element on the periodic table?', 32),
    (10, 'What is the largest planet in our solar system?', 32);


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



INSERT INTO `swp_project_summer_2024`.`comment`
(`content`, `date`, `status`, `parent_id`, `username`)
VALUES
    ('Cảm ơn bạn!', '2024-05-15 10:30:00', 'ACTIVE', 1, 'user02'),
    ('Bạn giải thích rõ hơn được không? Vì sao vậy?.', '2024-05-16 14:20:00', 'ACTIVE', 2, 'user02'),
    ('Bug thứ 2 xử lý như vậy có thể gây thêm lỗi!', '2024-05-17 09:45:00', 'ACTIVE', 12, 'user04'),
    ('Ồ, cảm ơn bạn', '2024-05-18 16:10:00', 'HIDDEN', 12, 'user02');

INSERT INTO `swp_project_summer_2024`.`comment`
(`content`, `date`, `status`, `post_id`, `username`)
VALUES
    ( 'Cảm ơn vì đã chia sẻ!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
    ( 'Test 1!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
    ( 'Test 2!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
    ( 'Test 3!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
    ( 'Test 4!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
    ( 'Test 5!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
    ( 'Test 6!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
    ( 'Test 7!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
    ( 'Test 8!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10');
