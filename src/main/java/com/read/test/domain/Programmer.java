package com.read.test.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by wang.read on 2016/3/10.
 *
 */
@Data
public class Programmer implements Serializable{
    private static final long serialVersionUID = 3846177445355712977L;
    private Integer id;
    private String name;
    private Integer age;
    private boolean male;
    private String gitHubSite;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public boolean isMale() {
//        return male;
//    }
//
//    public void setMale(boolean male) {
//        this.male = male;
//    }
//
//    public String getGitHubSite() {
//        return gitHubSite;
//    }
//
//    public void setGitHubSite(String gitHubSite) {
//        this.gitHubSite = gitHubSite;
//    }
//
//    @Override
//    public String toString() {
//        return "Programmer{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                ", male=" + male +
//                ", gitHubSite='" + gitHubSite + '\'' +
//                '}';
//    }
}
