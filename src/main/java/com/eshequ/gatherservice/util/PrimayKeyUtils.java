package com.eshequ.gatherservice.util;
import java.util.Random;
import java.util.UUID;

public class PrimayKeyUtils {

    /**
     * 生成32位随机数
     */
    public static String buildRandom() {

        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成16位随机数
     * @return
     */
    public static String getRandomBy16(){

        long currTime = System.currentTimeMillis();	//13位
        Random random = new Random();
        int s = random.nextInt(999)%(999-0+1) + 0;
        return String.valueOf(currTime)+s;

    }
}
