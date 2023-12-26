package com.go.together.Util;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class Util {

        // 암호화1
//        public static String pwSha256(String PW) {
//            String encrypted = "";
//            try {
//                // SHA-256 알고리즘을 사용하는 MessageDigest 인스턴스 생성
//                MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
//
//                // 주어진 문자열을 바이트 배열로 변환하여 해시 함수에 업데이트
//                sha256.update(PW.getBytes());
//
//                // 해시 함수를 통해 암호화된 바이트 배열을 얻음
//                byte[] hash = sha256.digest();
//
//                // 바이트 배열을 16진수 문자열로 변환
//                encrypted = bytesToHex(hash);
//
//                // 암호화된 값을 출력 (디버깅 용도로 사용)
//                System.out.println("Encrypted value: " + encrypted);
//            } catch (Exception e) {
//                // 예외 발생 시 스택 트레이스 출력
//                e.printStackTrace();
//            }
//
//            // 최종적으로 얻은 암호화된 16진수 문자열 반환
//            return encrypted;
//        }

    public static String pwSha256(String PW) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(PW.getBytes());
            byte[] hash = sha256.digest();
            return bytesToHex(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "암호화 오류가 발생했습니다"; // 예외가 발생한 경우 빈 문자열 반환
    }


        // 비밀번호 암호화
        private static String bytesToHex(byte[] bytes) {
            StringBuilder hex = new StringBuilder();
            for (byte b : bytes) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        }



    // 랜덤 문자열
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int STRING_LENGTH = 6;

    public static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(STRING_LENGTH);
        for (int i = 0; i < STRING_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String randomLettersAndNumbers = generateRandomString();
        System.out.println(randomLettersAndNumbers);
    }

}
