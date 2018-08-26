package com.william.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.william.entity.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Baozhikuan on 2018/8/24.
 */
public class RedisDao {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    private final JedisPool jedisPool;
    private RuntimeSchema<Goods> schema= RuntimeSchema.createFrom(Goods.class);

    public RedisDao(String ip,int port) {
        jedisPool=new JedisPool(ip,port);
    }

    public Goods getGood(long goodId){
        //redis操作逻辑
        try{
            Jedis jedis=jedisPool.getResource();
            try{
                String key="good:"+goodId;
                //实现自定义序列化
                byte[] bytes=jedis.get(key.getBytes());
                if(bytes!=null){
                    Goods good=schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes,good,schema);
                    return good;
                }

            }finally{
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    public String putGood(Goods good){
        try{
            Jedis jedis=jedisPool.getResource();
            try{
                String key = "good:"+good.getGoodId();
                byte[] bytes=ProtostuffIOUtil.toByteArray(good,schema,LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout=60*60;
                jedis.setex(key.getBytes(),timeout,bytes);
            }finally {
                jedis.close();
            }

        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
