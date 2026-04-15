package wanghui.airagproject.dao;

import org.apache.ibatis.annotations.Mapper;
import wanghui.airagproject.pojo.Doc;

import java.util.List;

/**
 * 类描述：插入逻辑
 *
 * @author LiuWangHui
 * @version 1.0.0
 * @date 2026/04/14 15:22:02
 */
@Mapper
public interface DocDao {

        int insert(Doc doc);
        List<Doc> selectAll();


}
