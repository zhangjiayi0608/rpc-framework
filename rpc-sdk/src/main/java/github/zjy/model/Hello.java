package github.zjy.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Hello
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 下午3:06
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Hello implements Serializable {
    private String message;
    private String description;
}
