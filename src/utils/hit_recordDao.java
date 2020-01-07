package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Record;

public class hit_recordDao {

    //データベース接続と結果取得のための変数
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public List<Record> getRecordCount(Integer genre, Integer value, Integer ages, Integer sex, String keyword){

        List<Record> records = new ArrayList<Record>();

        try{
            //1,2. ドライバを読み込み、DBに接続
            this.getConnection();

            //3. DBとやりとりする窓口（Statementオブジェクト）の作成
            StringBuffer buf = new StringBuffer();
            buf.append("  SELECT          ");
            buf.append("  id              ");
            buf.append("  FROM            ");
            buf.append("  record          ");
            buf.append("  WHERE           ");
            buf.append("  delete_flag = 0 ");

            if(genre != 0){
                buf.append("  AND          ");
                buf.append("  genre = ?    ");
            }

            if(value != 0){
                buf.append("  AND value= ? ");
            }

            if(ages != 0){
                buf.append("  AND user_age BETWEEN ? AND ?");
            }

            if(sex != 0){
                buf.append("  AND user_id IN ");
                buf.append("  ( SELECT id    ");
                buf.append("  FROM users     ");
                buf.append("  WHERE sex = ?) ");
            }

            if(keyword != null){
                buf.append("  AND id IN (         ");
                buf.append("  SELECT record_id    ");
                buf.append("  FROM tagtable       ");
                buf.append("  WHERE tag_id IN (   ");
                buf.append("  SELECT id           ");
                buf.append("  FROM tag            ");
                buf.append("  WHERE tag LIKE ?  ))");
            }

            buf.append("  ORDER BY        ");
            buf.append("  created_at      ");
            buf.append("  DESC            ");

            pstmt = con.prepareStatement(buf.toString());

            //4, 5. Select文の実行と結果を格納／代入
            int parameterIndex = 1;
            if(genre != 0){
                pstmt.setInt(parameterIndex, genre);
                parameterIndex += 1;
            }

            if(value != 0){
                pstmt.setInt(parameterIndex, value);
                parameterIndex += 1;
            }

            if(ages != 0){
                if (ages == 1){
                    pstmt.setInt(parameterIndex, 0);
                    parameterIndex += 1;
                    pstmt.setInt(parameterIndex, 19);
                    parameterIndex += 1;
                }
                else if (ages == 2){
                    pstmt.setInt(parameterIndex, 20);
                    parameterIndex += 1;
                    pstmt.setInt(parameterIndex, 29);
                    parameterIndex += 1;
                }
                else if (ages == 3){
                    pstmt.setInt(parameterIndex, 30);
                    parameterIndex += 1;
                    pstmt.setInt(parameterIndex, 39);
                    parameterIndex += 1;
                }
                else if (ages == 4){
                    pstmt.setInt(parameterIndex, 40);
                    parameterIndex += 1;
                    pstmt.setInt(parameterIndex, 49);
                    parameterIndex += 1;
                }
                else if (ages == 5){
                    pstmt.setInt(parameterIndex, 50);
                    parameterIndex += 1;
                    pstmt.setInt(parameterIndex, 59);
                    parameterIndex += 1;
                }
                else if (ages == 6){
                    pstmt.setInt(parameterIndex, 60);
                    parameterIndex += 1;
                    pstmt.setInt(parameterIndex, 69);
                    parameterIndex += 1;
                }
                else if (ages == 7){
                    pstmt.setInt(parameterIndex, 70);
                    parameterIndex += 1;
                    pstmt.setInt(parameterIndex, 150);
                    parameterIndex += 1;
                }
            }

            if(sex != 0){
                pstmt.setInt(parameterIndex, sex);
                parameterIndex += 1;
            }

            if(keyword != null){
                pstmt.setString(parameterIndex, keyword + "%");
                parameterIndex += 1;
                System.out.println(keyword);
            }


            rs = pstmt.executeQuery();

            //6. 結果を表示する
            while(rs.next()){
            //一件ずつRecordオブジェクトを生成して結果を詰める
                Record dto = new Record();
                dto.setId(rs.getInt("id"));
                //リストに追加
                records.add(dto);
            }



        }catch(SQLException e){
            //DBとの処理で何らかのエラーがあった場合の例外
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            //DBとの処理で何らかのエラーがあった場合の例外
            e.printStackTrace();
        }finally{

            this.close();
        }

        return records;
    }

    public void getConnection()throws SQLException, ClassNotFoundException{
        //1. ドライバのクラスをjava上で読み込む
        Class.forName("com.mysql.jdbc.Driver");

        //2. DBと接続する
        con = DriverManager.getConnection(
                "jdbc:mysql://localhost/movie_record_system?useSSL=false&useUnicode=true&characterEncoding=utf8",
                "root",
                "nana1004"
                );
    }

    private void close(){
        //7.接続を閉じる
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
    }
        if(pstmt != null){
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(con != null){
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

}
