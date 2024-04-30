package net.hamzaouggadi.todobe.dtos;

import java.time.LocalDateTime;

public record PostDTO(String code,
                      String title,
                      String content,
                      String pictureURL,
                      LocalDateTime publishedAt,
                      String writerEmail) {

}
