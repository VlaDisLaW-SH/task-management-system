INSERT INTO users (full_name, email, password, access_type, created_at)
VALUES
    ('John Doe', 'john.doe@example.com', 'securepassword123', 'USER', NOW()),
    ('Jane Smith', 'jane.smith@example.com', 'password456', 'ADMINISTRATOR', NOW()),
    ('Alice Johnson', 'alice.johnson@example.com', 'alicepass789', 'USER', NOW()),
    ('Bob Brown', 'bob.brown@example.com', 'bobpassword321', 'USER', NOW()),
    ('Charlie Davis', 'charlie.davis@example.com', 'charliepass654', 'ADMINISTRATOR', NOW());

INSERT INTO tasks (title, description, status, priority, created_by_id, assigner_id, created_at)
VALUES
    ('Task 1', 'Implement user authentication', 'NEW', 'HIGH', 2, 1, NOW()),
    ('Task 2', 'Design the database schema', 'IN_PROGRESS', 'LOW', 2, 3, NOW()),
    ('Task 3', 'Create API endpoints for tasks', 'CLOSED', 'HIGH', 2, 1, NOW()),
    ('Task 4', 'Write unit tests for task model', 'NEW', 'HIGH', 5, 4, NOW()),
    ('Task 5', 'Deploy application to production', 'CLOSED', 'LOW', 5, 3, NOW()),
    ('Task 6', 'Set up CI/CD pipeline', 'NEW', 'MIDDLE', 2, 3, NOW()),
    ('Task 7', 'Review pull requests', 'NEW', 'LOW', 2, 4, NOW()),
    ('Task 8', 'Update project documentation', 'IN_PROGRESS', 'MIDDLE', 2, 4, NOW()),
    ('Task 9', 'Fix bugs reported by users', 'IN_PROGRESS', 'HIGH', 5, 3, NOW()),
    ('Task 10', 'Conduct user testing sessions', 'CLOSED', 'LOW', 5, 4, NOW());

