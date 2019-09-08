package com.cx.question.user.model;

import com.cx.question.entity.user.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private Long id;

    private Long departmentId;

    private String authorization;

    private String userName;

}
