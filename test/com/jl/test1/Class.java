package com.jl.test1;

import java.util.List;

public class Class {  
    private String cname;  
    private List<Student> members;  
    public String getCname() {  
        return cname;  
    }  
    public void setCname(String cname) {  
        this.cname = cname;  
    }  
    public List<Student> getMembers() {  
        return members;  
    }  
    public void setMembers(List<Student> members) {  
        this.members = members;  
    }  
      
    public String toString(){  
        return "The Class Name is: " + cname + " " + "The members are: " + members;  
          
    }  
      
  
}  