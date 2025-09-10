use cantin_db;

create table test
(
    id   int auto_increment,
    name varchar(20) null,
    constraint test_pk
        primary key (id)
);
-- 2. 批量插入10条测试数据
INSERT INTO test (name)
VALUES
    ('测试数据1'),
    ('测试数据2'),
    ('测试数据3'),
    ('测试数据4'),
    ('测试数据5'),
    ('测试数据6'),
    ('测试数据7'),
    ('测试数据8'),
    ('测试数据9'),
    ('测试数据10');

-- 3. （可选）验证数据是否插入成功
SELECT * FROM test;


