package com.spoof.xuanke.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

   private String userName;
   private Integer userSex;
   private Date userBirthday;
   private String userEmail;
   private String userPhone;
}
