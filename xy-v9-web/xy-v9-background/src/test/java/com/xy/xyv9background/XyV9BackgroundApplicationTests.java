package com.xy.xyv9background;

import com.github.tobato.fastdfs.domain.fdfs.StorageNode;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XyV9BackgroundApplicationTests {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Autowired
    private TrackerClient trackerClient;

    @Test
    void contextLoads() {
//        fastFileStorageClient.deleteFile("http://192.168.0.103/group1/M00/00/00/wKgAZ13LfVOAJ-8FAACFoqRlCgc647.jpg");
//        StorageNode storeStorage = trackerClient.getStoreStorage();
//        trackerClient.listGroups().forEach(System.out::println);
    }

}
