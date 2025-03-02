INSERT INTO users (full_name, email, password, access_type, created_at)
VALUES
    ('John Doe', 'john.doe@example.com', 'securepassword123', 'USER', NOW()),
    ('Jane Smith', 'jane.smith@example.com', 'password456', 'ADMINISTRATOR', NOW()),
    ('Alice Johnson', 'alice.johnson@example.com', 'alicepass789', 'USER', NOW()),
    ('Bob Brown', 'bob.brown@example.com', 'bobpassword321', 'USER', NOW()),
    ('Charlie Davis', 'charlie.davis@example.com', 'charliepass654', 'USER', NOW());
