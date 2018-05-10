package cn.iclass.wechat.dals;

import cn.iclass.wechat.models.MiniContractModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MiniContractRepository extends JpaRepository<MiniContractModel, Long> {
    MiniContractModel findById(long id);
}

