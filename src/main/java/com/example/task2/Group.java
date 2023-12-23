package com.example.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Group<T> extends Task<T> {
    private List<Task<T>> tasks;

    public Group<T> addTask(Task<T> task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        return this;
    }

    @Override
    public void freeze() {
        super.freeze();
        for (Task<T> task : tasks) {
            task.freeze();
        }
    }

    @Override
    public void apply(T arg) {
        this.freeze();
        String groupId = UUID.randomUUID().toString();
        setHeader("groupId", groupId);

        for (Task<T> task : tasks) {
            if (task instanceof StampedTask) {
                ((StampedTask) task).stamp(groupId);
            }
            task.apply(arg);
        }
    }
}