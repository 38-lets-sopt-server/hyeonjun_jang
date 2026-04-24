package org.sopt.exception;

public class Validator {

    public static void TitleValidator(String title){
        if(title == null || title.isBlank() || title.length() > 50){
            throw new IllegalArgumentException("제목은 필수입니다!");
        }
    }

    public static void ContentValidator(String content){
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("내용은 필수입니다!");
        }
    }

}
