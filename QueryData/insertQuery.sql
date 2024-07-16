-- tat ca password là 123456
INSERT INTO swp_project_summer_2024.user (username, email, first_name, last_name, password, role, status, enrolled_date)
VALUES ('admin01', 'admin@gmail.com', 'ADMIN', 'PRO', '$2a$10$jsdoiYNm9wdTkpjuylSRbuCeF9xahmTfZ1IhOG7oNPy/Mlje2w5Aa', 'ADMIN', 'ACTIVE', '2023-05-21 10:30:00');

INSERT INTO swp_project_summer_2024.user (username, email, first_name, last_name, password, role, status, enrolled_date)
VALUES ('staff01', 'staff01@gmail.com', 'Staff', '01', '$2a$10$/xtkp9WKYeZ1vHl/CnYmz.kkDWMpYDy4cRft9NnDZ9AZByozrw34K', 'STAFF', 'ACTIVE', '2023-06-15 09:45:00');

INSERT INTO swp_project_summer_2024.user (username, email, first_name, last_name, password, role, status, enrolled_date)
VALUES ('staff02', 'staff02@gmail.com', 'Staff', '02', '$2a$10$/xtkp9WKYeZ1vHl/CnYmz.kkDWMpYDy4cRft9NnDZ9AZByozrw34K', 'STAFF', 'ACTIVE', '2023-07-02 14:20:00');

INSERT INTO swp_project_summer_2024.user (username, email, first_name, last_name, password, role, status, enrolled_date)
VALUES ('user01', 'user01@gmail.com', 'Dũng', 'Phan', '$2a$10$uj.HGYQb/bnhBjY0r22e/.9NGKtzNpaTgKBFOfXnWrayKaFW63/yq', 'USER', 'ACTIVE', '2023-08-19 11:00:00');

INSERT INTO swp_project_summer_2024.user (username, email, first_name, last_name, password, role, status, enrolled_date)
VALUES ('user02', 'user02@gmail.com', 'Thoa', 'Phạm', '$2a$10$ZsYKJ.jF9sQgY7kTDlwcweZaysKzYNh4nuP12M3ywBPU5Tz0YLBH.', 'USER', 'ACTIVE', '2023-09-07 16:30:00');

INSERT INTO swp_project_summer_2024.user (username, email, first_name, last_name, password, role, status, enrolled_date)
VALUES ('user03', 'user03@gmail.com', 'Quỳnh', 'Nguyễn', '$2a$10$ZsYKJ.jF9sQgY7kTDlwcweZaysKzYNh4nuP12M3ywBPU5Tz0YLBH.', 'USER', 'ACTIVE', '2023-10-25 08:15:00');

INSERT INTO swp_project_summer_2024.user (username, email, first_name, last_name, password, role, status, enrolled_date)
VALUES ('user04', 'user04@gmail.com', 'Hải', 'Vũ', '$2a$10$ZsYKJ.jF9sQgY7kTDlwcweZaysKzYNh4nuP12M3ywBPU5Tz0YLBH.', 'USER', 'ACTIVE', '2023-11-11 13:45:00');

INSERT INTO swp_project_summer_2024.user (username, email, first_name, last_name, password, role, status, enrolled_date)
VALUES ('user05', 'user05@gmail.com', 'Hiếu', 'Thái', '$2a$10$ZsYKJ.jF9sQgY7kTDlwcweZaysKzYNh4nuP12M3ywBPU5Tz0YLBH.', 'USER', 'ACTIVE', '2023-12-03 10:00:00');

INSERT INTO swp_project_summer_2024.user (username, email, first_name, last_name, password, role, status, enrolled_date)
VALUES ('user06', 'user06@gmail.com', 'Dương', 'Nguyễn', '$2a$10$ZsYKJ.jF9sQgY7kTDlwcweZaysKzYNh4nuP12M3ywBPU5Tz0YLBH.', 'USER', 'ACTIVE', '2024-01-18 15:20:00');

INSERT INTO swp_project_summer_2024.user (username, email, first_name, last_name, password, role, status, enrolled_date)
VALUES ('user07', 'user07@gmail.com', 'Trang', 'Kim', '$2a$10$ZsYKJ.jF9sQgY7kTDlwcweZaysKzYNh4nuP12M3ywBPU5Tz0YLBH.', 'USER', 'ACTIVE', '2024-02-22 09:10:00');

INSERT INTO swp_project_summer_2024.user (username, email, first_name, last_name, password, role, status, enrolled_date)
VALUES ('user08', 'user08@gmail.com', 'Mai', 'Lê', '$2a$10$ZsYKJ.jF9sQgY7kTDlwcweZaysKzYNh4nuP12M3ywBPU5Tz0YLBH.', 'USER', 'ACTIVE', '2024-03-14 17:45:00');

INSERT INTO swp_project_summer_2024.user (username, email, first_name, last_name, password, role, status, enrolled_date)
VALUES ('user09', 'user09@gmail.com', 'An', 'Nguyễn', '$2a$10$ZsYKJ.jF9sQgY7kTDlwcweZaysKzYNh4nuP12M3ywBPU5Tz0YLBH.', 'USER', 'ACTIVE', '2024-04-27 14:00:00');

INSERT INTO swp_project_summer_2024.user (username, email, first_name, last_name, password, role, status, enrolled_date)
VALUES ('user10', 'user10@gmail.com', 'Đức', 'Phạm', '$2a$10$ZsYKJ.jF9sQgY7kTDlwcweZaysKzYNh4nuP12M3ywBPU5Tz0YLBH.', 'USER', 'ACTIVE', '2024-05-30 11:30:00');



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


INSERT INTO `swp_project_summer_2024`.`subject`
(`code`, `is_active`, `name`, `semester`)
VALUES
    ('SSL101c', 1, 'Academic Skills for University Success', 1),
    ('PRF192', 1, 'Programming Fundamentals', 1),
    ('MAE101', 1, 'Mathematics for Engineering', 1),
    ('CEA201', 1, 'Computer Organization and Architecture', 1),
    ('CSI104', 0, 'Introduction to Computer Science', 1),
    ('PRO192', 0, 'Object-Oriented Programming', 2),
    ('MAD101', 0, 'Discrete mathematics', 2),
    ('OSG202', 1, 'Operating Systems', 2),
    ('NWC203c', 1, 'Computer Networking', 2),
    ('SSG104', 1, 'Communication and In-Group Working Skills', 2),
    ('JPD113', 1, 'Elementary Japanese 1-A1.1', 3),
    ('CSD201', 1, 'Data Structures and Algorithms', 3),
    ('DBI202', 1, 'Introduction to Databases', 3),
    ('LAB211', 1, 'OOP with Java Lab', 3),
    ('WED201c', 1, 'Web Design', 3),
    ('MAS291', 1, 'Statistics and Probability', 4),
    ('JPD123', 1, 'Elementary Japanese 1-A1.2', 4),
    ('IOT102', 1, 'Internet vạn vật', 4),
    ('PRJ301', 1, 'Java Web Application Development', 4),
    ('SWE201c', 1, 'Introduction to Software Engineering', 4),
    ('SWP391', 1, 'Application development project', 5),
    ('ITE302c', 1, 'Ethics in IT', 5),
    ('SWR302', 1, 'Software Requirement', 5),
    ('SWT301', 1, 'Software Testing', 5),
    ('PRN212', 1, 'Basis Cross-Platform Application Programming With .NET', 5),
    ('OJT202', 1, 'On the job training', 6),
    ('ENW492c', 1, 'Writing Research Papers', 6),
    ('PRN221', 1, 'Advanced Cross-Platform Application Programming With .NET', 7),
    ('EXE101', 1, 'Experiential Entrepreneurship 1', 7),
    ('PRU212', 1, 'C# Programming and Unity', 7),
    ('PRM392', 1, 'Mobile Programming', 7),
    ('SWD392', 1, 'SW Architecture and Design', 7),
    ('EXE201', 1, 'Experiential Entrepreneurship 2', 8),
    ('MLN122', 1, 'Political economics of Marxism – Leninism', 8),
    ('PMG202c', 1, 'Project management', 8),
    ('WDU203c', 1, 'UI/UX Design', 8),
    ('PRN231', 1, 'Building Cross-Platform Back-End Application With .NET', 8),
    ('MLN111', 1, 'Philosophy of Marxism – Leninism', 8),
    ('MLN131', 1, 'Scientific socialism', 9),
    ('VNR202', 1, 'History of Việt Nam Communist Party', 9),
    ('SEP490', 1, 'SE Capstone Project', 9),
    ('HCM202', 1, 'Ho Chi Minh Ideology', 9);

# # RESET COMMENT TABLE
# START TRANSACTION;
#
# SET FOREIGN_KEY_CHECKS = 0;
# DELETE FROM comment where username is not null;
# SET FOREIGN_KEY_CHECKS = 1;
# ALTER TABLE comment AUTO_INCREMENT = 1;
#
# COMMIT;
#
# INSERT INTO `swp_project_summer_2024`.`comment`
# (`id`, `content`, `date`, `status`, `post_id`, `username`)
# VALUES
#     (1, 'Bài hay quá ạ!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user01'),
#     (2, 'Tôi không đồng ý hoàn toàn với quan điểm này.', '2024-05-16 14:20:00', 'ACTIVE', 15, 'user04'),
#     (3, 'Tuyệt vời, bạn đã giải quyết vấn đề rất tốt!', '2024-05-17 09:45:00', 'ACTIVE', 15, 'user03'),
#     (4, 'Bài này cần thêm thông tin chi tiết hơn.', '2024-05-18 16:10:00', 'HIDDEN', 5, 'user04'),
#     (5, 'Tôi thích cách bạn trình bày ý tưởng, rất logic.', '2024-05-19 11:00:00', 'ACTIVE', 5, 'user05'),
#     (6, 'Cảm ơn bạn đã chia sẻ kinh nghiệm ^^.', '2024-05-20 13:40:00', 'ACTIVE', 5, 'user06'),
#     (7, 'Bài viết này cần sửa lỗi chính tả, mong bạn kiểm tra lại.', '2024-05-21 08:50:00', 'HIDDEN', 3, 'user07'),
#     (8, 'Tôi hoàn toàn đồng ý với quan điểm của bạn, rất thuyết phục.', '2024-05-22 15:30:00', 'ACTIVE', 3, 'user08'),
#     (9, 'Bài viết này rất hấp dẫn, tôi thích nó lắm!', '2024-05-23 10:00:00', 'ACTIVE', 11, 'user09'),
#     (10, 'Bạn ơi, có thể bổ sung thêm thông tin về chủ đề này được không ạ?', '2024-05-24 14:15:00', 'HIDDEN', 11, 'user10'),
#     (11, 'Đây là một bài viết rất hữu ích!', '2024-06-22', 'ACTIVE', 4, 'user01'),
#     (12, 'Tôi không hoàn toàn đồng ý với quan điểm của tác giả.', '2024-06-23', 'ACTIVE', 4, 'user02'),
#     (13, 'Rất thú vị, tôi có vài ý kiến để bổ sung...', '2024-06-24', 'ACTIVE', 5, 'user03'),
#     (14, 'Tuyệt vời, tôi hoàn toàn ủng hộ tác giả!', '2024-06-25', 'ACTIVE', 5, 'user04'),
#     (15, 'Tôi không hiểu lập luận chính của bài viết. Có thể giải thích rõ hơn không?', '2024-06-26', 'ACTIVE', 6, 'user05'),
#     (16, 'Rất thú vị, tác giả đã trình bày vấn đề rất logic.', '2024-06-27', 'ACTIVE', 6, 'user06'),
#     (17, 'Tôi nghĩ rằng cần cân nhắc thêm một số vấn đề khác.', '2024-06-28', 'ACTIVE', 7, 'user07'),
#     (18, 'Hoàn toàn đồng ý, bài viết rất hay!', '2024-06-29', 'ACTIVE', 7, 'user08'),
#     (19, 'Quan điểm của tác giả khá thuyết phục, nhưng tôi vẫn có một vài ý kiến khác.', '2024-06-30', 'ACTIVE', 8, 'user09'),
#     (20, 'Rất hữu ích, cảm ơn tác giả đã chia sẻ!', '2024-07-01', 'ACTIVE', 8, 'user10'),
#     (21, 'Tôi không hoàn toàn hiểu ý tưởng chính của bài viết. Có thể giải thích thêm không?', '2024-07-02', 'ACTIVE', 9, 'user01'),
#     (22, 'Bài viết rất hay, tôi thích cách tiếp cận của tác giả.', '2024-07-03', 'ACTIVE', 9, 'user02'),
#     (23, 'Rất thú vị, tôi nghĩ cần có thêm quan điểm khác.', '2024-07-04', 'ACTIVE', 10, 'user03'),
#     (24, 'Tuyệt vời, tôi hoàn toàn đồng ý với những gì tác giả nói.', '2024-07-05', 'ACTIVE', 10, 'user04'),
#     (25, 'Tôi không hiểu lý do tác giả lại đưa ra kết luận như vậy. Có thể giải thích thêm không?', '2024-07-06', 'ACTIVE', 11, 'user05'),
#     (26, 'Rất hay, tác giả đã trình bày vấn đề rất logic và thuyết phục.', '2024-07-07', 'ACTIVE', 11, 'user06'),
#     (27, 'Tôi có một số ý kiến khác, nhưng nhìn chung bài viết rất tốt.', '2024-07-08', 'ACTIVE', 12, 'user07'),
#     (28, 'Hoàn toàn đồng ý, đây là một bài viết rất chất lượng.', '2024-07-09', 'ACTIVE', 12, 'user08'),
#     (29, 'Quan điểm của tác giả khá thú vị, tôi muốn được biết thêm về những ý tưởng này.', '2024-07-10', 'ACTIVE', 13, 'user09'),
#     (30, 'Rất hữu ích, cảm ơn tác giả đã chia sẻ những hiểu biết của mình.', '2024-07-11', 'ACTIVE', 13, 'user10'),
#     (31, 'Bài viết thật tuyệt vời, tôi đã học được rất nhiều điều mới từ đây.', '2024-07-12', 'ACTIVE', 14, 'user01'),
#     (32, 'Tôi không hoàn toàn đồng ý với quan điểm của tác giả, nhưng vẫn rất hữu ích.', '2024-07-13', 'ACTIVE', 14, 'user02'),
#     (33, 'Rất thú vị, tôi muốn được biết thêm về những ý tưởng này.', '2024-07-14', 'ACTIVE', 15, 'user03'),
#     (34, 'Tuyệt vời, tôi hoàn toàn ủng hộ tác giả!', '2024-07-15', 'ACTIVE', 15, 'user04'),
#     (35, 'Tôi không hiểu lập luận chính của bài viết. Có thể giải thích rõ hơn không?', '2024-07-16', 'ACTIVE', 16, 'user05'),
#     (36, 'Rất thú vị, tác giả đã trình bày vấn đề rất logic.', '2024-07-17', 'ACTIVE', 16, 'user06'),
#     (37, 'Tôi nghĩ rằng cần cân nhắc thêm một số vấn đề khác.', '2024-07-18', 'HIDDEN', 17, 'user07'),
#     (38, 'Hoàn toàn đồng ý, bài viết rất hay!', '2024-07-19', 'ACTIVE', 17, 'user08'),
#     (39, 'Quan điểm của tác giả khá thuyết phục, nhưng tôi vẫn có một vài ý kiến khác.', '2024-07-20', 'ACTIVE', 18, 'user09'),
#     (40, 'Rất hữu ích, cảm ơn tác giả đã chia sẻ!', '2024-07-21', 'ACTIVE', 18, 'user10'),
#     (41, 'Tôi không hoàn toàn hiểu ý tưởng chính của bài viết. Có thể giải thích thêm không?', '2024-07-22', 'ACTIVE', 19, 'user01'),
#     (42, 'Bài viết rất hay, tôi thích cách tiếp cận của tác giả.', '2024-07-23', 'ACTIVE', 19, 'user02'),
#     (43, 'Rất thú vị, tôi nghĩ cần có thêm quan điểm khác.', '2024-07-24', 'ACTIVE', 20, 'user03'),
#     (44, 'Tuyệt vời, tôi hoàn toàn đồng ý với những gì tác giả nói.', '2024-07-25', 'ACTIVE', 20, 'user04'),
#     (45, 'Tôi không hiểu lý do tác giả lại đưa ra kết luận như vậy. Có thể giải thích thêm không?', '2024-07-26', 'ACTIVE', 21, 'user05'),
#     (46, 'Rất hay, tác giả đã trình bày vấn đề rất logic và thuyết phục.', '2024-07-27', 'ACTIVE', 21, 'user06'),
#     (47, 'Tôi có một số ý kiến khác, nhưng nhìn chung bài viết rất tốt.', '2024-07-28', 'ACTIVE', 22, 'user07'),
#     (48, 'Hoàn toàn đồng ý, đây là một bài viết rất chất lượng.', '2024-07-29', 'ACTIVE', 22, 'user08'),
#     (49, 'Quan điểm của tác giả khá thú vị, tôi muốn được biết thêm về những ý tưởng này.', '2024-07-30', 'ACTIVE', 23, 'user09'),
#     (50, 'Rất hữu ích, cảm ơn tác giả đã chia sẻ những hiểu biết của mình.', '2024-07-31', 'ACTIVE', 23, 'user10'),
#     (51, 'Bài viết thật tuyệt vời, tôi đã học được rất nhiều điều mới từ đây.', '2024-08-01', 'ACTIVE', 24, 'user01'),
#     (52, 'Tôi không hoàn toàn đồng ý với quan điểm của tác giả, nhưng vẫn rất hữu ích.', '2024-08-02', 'ACTIVE', 24, 'user02'),
#     (53, 'Rất thú vị, tôi muốn được biết thêm về những ý tưởng này.', '2024-08-03', 'ACTIVE', 25, 'user03'),
#     (54, 'Tuyệt vời, tôi hoàn toàn ủng hộ tác giả!', '2024-08-04', 'ACTIVE', 25, 'user04'),
#     (55, 'Tôi không hiểu lập luận chính của bài viết. Có thể giải thích rõ hơn không?', '2024-08-05', 'ACTIVE', 26, 'user05'),
#     (56, 'Rất thú vị, tác giả đã trình bày vấn đề rất logic.', '2024-08-06', 'ACTIVE', 26, 'user06'),
#     (57, 'Tôi nghĩ rằng cần cân nhắc thêm một số vấn đề khác.', '2024-08-07', 'ACTIVE', 27, 'user07'),
#     (58, 'Hoàn toàn đồng ý, bài viết rất hay!', '2024-08-08', 'ACTIVE', 27, 'user08'),
#     (59, 'Quan điểm của tác giả khá thuyết phục, nhưng tôi vẫn có một vài ý kiến khác.', '2024-08-09', 'ACTIVE', 28, 'user09'),
#     (60, 'Rất hữu ích, cảm ơn tác giả đã chia sẻ!', '2024-08-10', 'ACTIVE', 28, 'user10'),
#     (61, 'Bài viết rất hữu ích, tôi đã học được nhiều thông tin mới từ đây.', '2024-07-03', 'ACTIVE', 23, 'user01'),
#     (62, 'Tôi đồng ý với những điểm chính mà tác giả đề cập. Rất đáng để đọc.', '2024-07-04', 'ACTIVE', 13, 'user02'),
#     (63, 'Cảm ơn tác giả đã chia sẻ bài viết này. Nó thật sự gợi mở nhiều suy nghĩ.', '2024-07-05', 'ACTIVE', 13, 'user03'),
#     (64, 'Những quan điểm trong bài rất thú vị. Tôi sẽ xem xét kỹ hơn.', '2024-07-06', 'ACTIVE', 1, 'user04'),
#     (65, 'Một bài viết rất đáng đọc. Tôi hy vọng tác giả sẽ viết thêm nhiều bài như thế này.', '2024-07-07', 'HIDDEN', 2, 'user05'),
#     (66, 'Thông tin trong bài rất chi tiết và rõ ràng. Tôi cảm thấy đã được khai sáng.', '2024-07-08', 'ACTIVE', 3, 'user06'),
#     (67, 'Bài viết này đã giúp tôi hiểu rõ hơn về vấn đề này. Cảm ơn tác giả!', '2024-07-09', 'ACTIVE', 13, 'user07'),
#     (68, 'Tôi thực sự ấn tượng với cách trình bày chi tiết và dễ hiểu của bài viết.', '2024-07-10', 'ACTIVE', 12, 'user08'),
#     (69, 'Đây là một bài viết rất đáng đọc. Tôi đã học được nhiều điều mới.', '2024-07-11', 'ACTIVE', 11, 'user09'),
#     (70, 'Cảm ơn tác giả đã chia sẻ những kiến thức quý báu này. Rất hữu ích!', '2024-07-12', 'ACTIVE', 31, 'user10'),
#     (71, 'Tôi rất thích cách tiếp cận vấn đề của tác giả. Nó rất sáng tạo và thú vị.', '2024-07-13', 'ACTIVE', 25, 'user01'),
#     (72, 'Bài viết này đã giúp tôi nhìn nhận vấn đề từ một góc độ mới. Rất đáng để đọc.', '2024-07-14', 'ACTIVE', 28, 'user02'),
#     (73, 'Tôi hoàn toàn đồng tình với những quan điểm được trình bày trong bài viết này.', '2024-07-15', 'ACTIVE', 27, 'user03'),
#     (74, 'Đây là một bài viết rất hay và đầy thông tin. Tôi đã rất thích đọc nó.', '2024-07-16', 'ACTIVE', 26, 'user04'),
#     (75, 'Sau khi đọc bài viết này, tôi có những suy nghĩ và nhận định mới về vấn đề.', '2024-07-17', 'ACTIVE', 25, 'user05'),
#     (76, 'Tôi đánh giá cao sự cẩn thận và chi tiết trong cách trình bày của tác giả.', '2024-07-18', 'ACTIVE', 31, 'user06'),
#     (77, 'Bài viết này đã giúp tôi hiểu rõ hơn về chủ đề này. Rất cảm ơn tác giả!', '2024-07-19', 'ACTIVE', 32, 'user07'),
#     (78, 'Tôi thực sự ấn tượng với cách phân tích vấn đề sâu sắc của tác giả.', '2024-07-20', 'ACTIVE', 33, 'user08'),
#     (79, 'Đây là một trong những bài viết hay nhất mà tôi đã đọc về chủ đề này.', '2024-07-21', 'ACTIVE', 34, 'user09'),
#     (80, 'Tôi rất ấn tượng với sự logic và thuyết phục trong cách trình bày của tác giả.', '2024-07-22', 'ACTIVE', 40, 'user10');
#
# INSERT INTO `swp_project_summer_2024`.`comment`
# (`content`, `date`, `status`, `parent_id`, `username`)
# VALUES
#     ('Cảm ơn bạn!', '2024-05-15 10:30:00', 'ACTIVE', 1, 'user02'),
#     ('Bạn giải thích rõ hơn được không? Vì sao vậy?.', '2024-05-16 14:20:00', 'ACTIVE', 2, 'user02'),
#     ('Bug thứ 2 xử lý như vậy có thể gây thêm lỗi!', '2024-05-17 09:45:00', 'ACTIVE', 82, 'user04'),
#     ('Ồ, cảm ơn bạn', '2024-05-18 16:10:00', 'ACTIVE', 82, 'user02'),
#     ('My veryyyyyyy loooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong comment!', '2024-05-17 09:45:00', 'ACTIVE', 2, 'user04'),
#     ('1!', '2024-05-17 09:45:00', 'ACTIVE', 2, 'user04'),
#     ('2!', '2024-05-17 09:45:00', 'ACTIVE', 2, 'user04'),
#     ('3!', '2024-05-17 09:45:00', 'ACTIVE', 2, 'user04'),
#     ('4!', '2024-05-17 09:45:00', 'ACTIVE', 2, 'user04'),
#     ('5!', '2024-05-17 09:45:00', 'ACTIVE', 2, 'user04'),
#     ('6!', '2024-05-17 09:45:00', 'ACTIVE', 2, 'user04'),
#     ('7!', '2024-05-17 09:45:00', 'ACTIVE', 2, 'user04'),
#     ('8!', '2024-05-17 09:45:00', 'ACTIVE', 2, 'user04');
#
# INSERT INTO `swp_project_summer_2024`.`comment`
# (`content`, `date`, `status`, `post_id`, `username`)
# VALUES
#     ( 'Cảm ơn vì đã chia sẻ!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
#     ( 'Test 1!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
#     ( 'Test 2!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
#     ( 'Test 3!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
#     ( 'Test 4!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
#     ( 'Test 5!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
#     ( 'Test 6!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
#     ( 'Test 7!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10'),
#     ( 'Test 8!', '2024-05-15 10:30:00', 'ACTIVE', 15, 'user10');
