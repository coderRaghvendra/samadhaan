package com.samadhaan4u.dto.constant;

import com.samadhaan4u.model.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by raghvendra.mishra on 05/04/18.
 */
public enum UserType implements Type<User>{
    ADMIN(1, "Administrator"),
    USER(2, "User");

    private static final Map<Integer, UserType> types = new HashMap<Integer, UserType>();

    static {
        for (UserType type : UserType.values()) {
            if (types.get(type.id) == null) {
                types.put(type.id, type);
            }
//            else {
//                throw new Exception("Duplicate id: " + type.id);
//            }
        }
    }
    private final int id;
    private final String description;

    UserType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static UserType valueOf(int id) {
        return types.get(id);
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String description() {
        return description;
    }
}
