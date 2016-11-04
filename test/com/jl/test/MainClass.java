package com.jl.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MainClass {  
	  
    public static void main(String[] args) {  
        TestJsonBean();  
      //TestJsonAttribute();  
       // TestJsonArray();          
    }  
  
    @SuppressWarnings("rawtypes")  
    private static void TestJsonArray() {  
        Student student1 = new Student();  
        student1.setId(1);  
        student1.setName("jag");  
        student1.setSex("man");  
        student1.setAge(25);  
        student1.setHobby(new String[]{"篮球","游戏"});  
          
        Student student2 = new Student();  
        student2.setId(2);  
        student2.setName("tom");  
        student2.setSex("woman");  
        student2.setAge(23);  
        student2.setHobby(new String[]{"上网","跑步"});  
          
        List<Student> list = new ArrayList<Student>();  
        list.add(student1);  
        list.add(student2);  
          
        JSONArray jsonArray = JSONArray.fromObject(list);  
        System.out.println(jsonArray.toString());  
       
       // JSONArray new_jsonArray=JSONArray.fromObject(jsonArray.toArray());  
        Collection java_collection=JSONArray.toCollection(jsonArray);  
        if(java_collection!=null && !java_collection.isEmpty())  
        {  
            Iterator it=java_collection.iterator();  
            while(it.hasNext())  
            {  
                JSONObject jsonObj=JSONObject.fromObject(it.next());  
                Student stu=(Student) JSONObject.toBean(jsonObj,Student.class);  
                System.out.println(stu.getName());  
            }  
        }  
    }  
  
    private static void TestJsonAttribute() {  
        /** 
         * 创建json对象并为该对象设置属性 
         */  
        JSONObject jsonObj = new JSONObject();  
        jsonObj.put("Int_att",25);//添加int型属性  
        jsonObj.put("String_att","str");//添加string型属性  
        jsonObj.put("Double_att",12.25);//添加double型属性  
        jsonObj.put("Boolean_att",true);//添加boolean型属性  
        //添加JSONObject型属性  
        JSONObject jsonObjSon = new JSONObject();  
        jsonObjSon.put("id", 1);  
        jsonObjSon.put("name", "tom");  
        jsonObj.put("JSONObject_att",jsonObjSon);  
        //添加JSONArray型属性  
        JSONArray jsonArray = new JSONArray();  
        jsonArray.add("array0");  
        jsonArray.add("array1");  
        jsonArray.add("array2");  
        jsonArray.add("array3");  
        jsonObj.put("JSONArray_att", jsonArray);  
        System.out.println(jsonObj.toString());  
        System.out.println("Int_att:"+jsonObj.getInt("Int_att"));  
        System.out.println("String_att:"+jsonObj.getString("String_att"));  
        System.out.println("Double_att:"+jsonObj.getDouble("Double_att"));  
        System.out.println("Boolean_att:"+jsonObj.getBoolean("Boolean_att"));  
        System.out.println("JSONObject_att:"+jsonObj.getJSONObject("JSONObject_att"));  
        System.out.println("JSONArray_att:"+jsonObj.getJSONArray("JSONArray_att"));  
    }  
  
    /** 
     * java对象与json对象互相转换 
     */  
    private static void TestJsonBean() {  
        /** 
         * 创建java对象 
         */  
        Student student = new Student();  
        student.setId(1);  
        student.setName("jag");  
        student.setSex("man");  
        student.setAge(25);  
        student.setHobby(new String[]{"篮球","上网","跑步","游戏"});  
        /** 
         * java对象转换成json对象，并获取json对象属性 
         */  
        JSONObject jsonStu = JSONObject.fromObject(student);  
        System.out.println(jsonStu.toString());  
        System.out.println(jsonStu.getJSONArray("hobby"));  
        /** 
         * json对象转换成java对象，并获取java对象属性 
         */  
        Student stu = (Student) JSONObject.toBean(jsonStu, Student.class);  
        System.out.println(stu.getName());  
        /** 
         * 创建json对象 
         */  
        JSONObject jsonObj = new JSONObject();  
        jsonObj.put("id",1);  
        jsonObj.put("name","张勇");  
        jsonObj.put("sex","男");  
        jsonObj.put("age",24);  
        jsonObj.put("hobby",new String[]{"上网","游戏","跑步","音乐"});  
        System.out.println(jsonObj.toString());  
        /** 
         * json对象转换成java对象 
         */  
        Student stud = (Student) JSONObject.toBean(jsonObj,Student.class);  
        System.out.println(stud.getName());       
    }  
}  
