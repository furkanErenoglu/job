package com.jobportal.job.loggers.messages;

public class ExperienceMessage {
    private ExperienceMessage() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static final String PROFILE_NOT_FOUND = "Profile not found with id: ";
    public static final String EXPERIENCE_NOT_FOUND = "Experience not found with id: ";
    public static final String EXPERIENCE_CREATED_SUCCESS = "Experience created successfully";
    public static final String EXPERIENCE_DELETED_SUCCESS = "Experience deleted successfully";
    public static final String EXPERIENCE_UPDATED_SUCCESS = "Experience updated successfully";
    public static final String EXPERIENCE_DELETED_LOG = "Experience with id %d deleted successfully.";
}
