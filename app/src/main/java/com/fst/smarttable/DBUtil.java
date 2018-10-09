package com.fst.smarttable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBUtil
{
    static Connection conn=null;
    static String ip="47.99.159.220";
    private static int connect(String ip, String user, String pwd, String db)
    {
        //Connection con = null;
        try
        {
            //if(conn==null)
            {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:jtds:sqlserver://" + ip + ":1433/" + db + ";useunicode=true;characterEncoding=UTF-8", user, pwd);
            }
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return -1;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return -2;
        }
        return 1;
        //return conn;
    }
    public static int QueryScrthrtCountByPrjid(String prjid)
    {
        int recordCount=0;
        try
        {
            //Connection conn = getSQLConnection("192.168.0.103", "sa", "020130lhp", "sftmnt");
            if(connect(ip, "sa", "020130lhp", "sftmnt")>0)
            {
                String sql = "select count(*) as total from SCRTHRT where PRJID='"+prjid+"'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                recordCount = rs.getInt("total");

            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return recordCount;
    }
    public static ArrayList<Scrthrt> QueryScrthrtByPrjid(String prjid,int start,int limit)
    {
        ArrayList<Scrthrt> list = new ArrayList<>();
        try
        {
            //Connection conn = getSQLConnection("192.168.0.103", "sa", "020130lhp", "sftmnt");
            if(connect(ip, "sa", "020130lhp", "sftmnt")>0)
            {
                String sql = "select * from SCRTHRT where SID in (select top "+limit+" SID from SCRTHRT where PRJID='";
                sql=sql+  prjid +"' and SID not in (select top "+start+" SID from SCRTHRT where PRJID='"+prjid+"'))  ";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                int i=0;
                while (rs.next())
                {
                    //UserInfo u=new UserInfo(rs.getString("ACCT"),rs.getString("NAME"));
                    //list.add(u);
                    //list.add(new UserInfo());
                    Scrthrt s=new Scrthrt();
                    s.setSid(rs.getString("SID"));
                    s.setName(rs.getString("NAME"));
                    s.setSubtp(rs.getString("SUBTP"));
                    s.setDesp(rs.getString("Desp"));
                    s.setJcyj(rs.getString("JCYJ"));
                    s.setLevel(rs.getString("LEVEL"));
                    if(i%2==0)
                        s.setChecked(false);
                    else
                        s.setChecked(true);
                    i++;
                    list.add(s);
                }
                rs.close();
                stmt.close();
                //conn.close();
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[] args)
    {
        //QuerySQL();
    }
}
