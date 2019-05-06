package com.cn.ln.Dao;


import com.cn.ln.Beans.Dept;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class DeptSqlProvider {

    public String selectDept(final Map map){

        String sql = new SQL(){
            {
                this.SELECT("*");
                this.FROM("dept_inf");
                if (map.get("name") != null && !"".equals(map.get("name"))){
                    this.WHERE("name like '%' #{name} '%'");
                }
            }
        }.toString();
        sql = sql + " limit #{start},#{pageSize}";
        return sql;

        /*StringBuffer sql = new StringBuffer();
        sql.append("select * from dept_inf");
        if (map.get("name") != null && !"".equals(map.get("name"))){
            sql.append(" where name like '%' #{name} '%'");
        }
        sql.append(" limit #{start},#{pageSize}");
        return sql.toString();*/
    }


    public String selectDeptCount(final String name){
        String sql = new SQL(){
            {
                this.SELECT("count(*)");
                this.FROM("dept_inf");
                if (name != null && !"".equals(name)){
                    this.WHERE("name like '%' #{name} '%'");
                }
            }
        }.toString();
        return sql;
    }

    public String updateDept(final Dept dept){
        String sql = new SQL(){
            {
                this.UPDATE("dept_inf");
                if (dept.getName() != null && !"".equals(dept.getName())){
                    this.SET("name = #{name}");
                }
                if (dept.getRemark() != null && !"".equals(dept.getRemark())){
                    this.SET("remark = #{remark}");
                }
                this.WHERE("id=#{id}");
            }
        }.toString();
        return sql;
    }

    public String deleteDept(Map map){
        //delete from dept_inf where id in ()
        StringBuffer sql = new StringBuffer();
        sql.append("delete from dept_inf where id in (");
        Integer[] ids = (Integer[]) map.get("ids");
        for (Integer id:ids){
            sql.append(id+",");
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");
        return sql.toString();
    }

    public String insertDept(final Dept dept){
        //insert into dept_inf (name,remark) values(#{name},#{remark})
        String sql = new SQL(){
            {
                this.INSERT_INTO("dept_inf");
                if (dept.getName() != null && !"".equals(dept.getName())){
                    this.VALUES("name","#{name}");
                }
                if (dept.getRemark() != null && !"".equals(dept.getRemark())){
                    this.VALUES("remark","#{remark}");
                }
            }
        }.toString();
        return sql;
    }

}
