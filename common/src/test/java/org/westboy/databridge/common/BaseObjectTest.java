package org.westboy.databridge.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 基类测试类
 *
 * @author mumu
 * @since 2023/4/8 09:40
 */
class BaseObjectTest {

    @Test
    void testToString() {
        Student student = new Student();
        student.setName("xw");
        student.setAge(18);
        System.out.println(ToStringBuilder.reflectionToString(student, ToStringStyle.DEFAULT_STYLE));
        System.out.println(ToStringBuilder.reflectionToString(student, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(ToStringBuilder.reflectionToString(student, ToStringStyle.NO_FIELD_NAMES_STYLE));
        System.out.println(ToStringBuilder.reflectionToString(student, ToStringStyle.SHORT_PREFIX_STYLE));
        System.out.println(ToStringBuilder.reflectionToString(student, ToStringStyle.SIMPLE_STYLE));
    }


    static class Student extends BaseObject {

        private String name;
        private Integer age;

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }
    }
}