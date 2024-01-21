# VNSC Autotest

# Run test

## Setup configPath for testcase
- Open src/main/java/constants/ConfigPath.java to configPath by environment, follow code below
![Screen Shot 2024-01-22 at 00.35.47.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fnt%2Ftc74bd1d6y156x98sx1xyf3r0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_BEHPgJ%2FScreen%20Shot%202024-01-22%20at%2000.35.47.png)


- Chạy test cho môi trường nào thì thêm giá trị env tương ứng vào VM options(hình dưới):
![Screen Shot 2024-01-22 at 00.32.16.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fnt%2Ftc74bd1d6y156x98sx1xyf3r0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_6cYzOu%2FScreen%20Shot%202024-01-22%20at%2000.32.16.png)
Hiện setup chạy test áp dụng cho môi trường dev và prod (nếu khi run tcs ko truyền env thì load default)