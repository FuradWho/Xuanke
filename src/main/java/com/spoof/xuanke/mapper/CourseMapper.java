package com.spoof.xuanke.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spoof.xuanke.pojo.Course;
import com.spoof.xuanke.pojo.GetCourse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 13375
 */
@Repository
public interface CourseMapper extends BaseMapper<Course> {


    @Select("SELECT t_course.course_id,t_course.course_credit,t_course.course_cycle,t_course.course_name,t_course_type.name,t_student.stu_name FROM t_course,t_course_type,t_student WHERE t_course.course_teacher_id=t_student.stu_id and t_course.courseType_id = t_course_type.id and t_course.course_id = #{stuId} limit #{startIndex},#{pageSize}" )
    List<GetCourse> getCourseByStuId(@Param("startIndex")int startIndex , @Param("pageSize")int pageSize ,@Param("stuId") int stuId );

    @Select({
            "<script>",
                "SELECT",
                "t_course.course_id,t_course.course_credit,t_course.course_cycle,t_course.course_name,t_course_type.name,t_student.stu_name",
                "FROM t_course,t_course_type,t_student",
                "WHERE t_course.course_teacher_id=t_student.stu_id and t_course.courseType_id = t_course_type.id and t_course.course_id not in",
                    "<foreach collection='stuId' item='id' open='(' separator=',' close=')'>",
                    "#{id}",
                    "</foreach>",
                "limit #{startIndex},#{pageSize}",
            "</script>"
    })
    List<GetCourse> getNewCourse(@Param("startIndex")int startIndex , @Param("pageSize")int pageSize ,@Param("stuId") List<Integer> stuId );



    @Select("select course_id,course_credit,course_cycle,course_grade,course_name from t_course where course_teacher_id = #{teacherId} limit #{startIndex},#{pageSize}")
    List<Course> getCourseByTeacherId(@Param("startIndex")int startIndex , @Param("pageSize")int pageSize,@Param("teacherId") int teacherId);

}
