package kokoroAme.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CountBlog {
	private LocalDateTime  time;
	private Integer count;
}
