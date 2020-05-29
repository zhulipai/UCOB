package com.ucob.controller;

import com.ucob.contract.CorporationControllerDemo;
import com.ucob.contract.CorporationStorageDemo;
import com.ucob.contract.UCOBNodeController;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.junit.Test;

import java.math.BigInteger;

/**
 * @ClassName Corporation1
 * @Description 调用 CorporationController 和 CorporationStorage 合约的控制器类
 * @Author Pai
 * @Date 2020/5/27 14:25
 **/
public class CorporationController extends BaseController {

    //使用部署者地址，转移CorporationStorage合约所有权到CorporationController
    @Test
    public void CorporationStorageDemo_transferOwnership() {
        try {
            this.initialize(deployAccount);
            CorporationStorageDemo contract = CorporationStorageDemo.load(CORPORATIONSTORAGEDEMOADDRESS1, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = contract.transferOwnership(CORPORATIONCONTROLLERDEMOADDRESS1).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //检查所有权是否成功转移
    @Test
    public void CorporationControllerDemo_checkCorporationStorageSafety() {
        try {
            this.initialize(deployAccount);
            CorporationControllerDemo contract = CorporationControllerDemo.load(CORPORATIONCONTROLLERDEMOADDRESS1, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            Boolean receipt = contract.checkCorporationStorageSafety().send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //检查合约是否初始化
    @Test
    public void CorporationStorageDemo_initialized() {
        try {
            this.initialize(deployAccount);
            CorporationStorageDemo contract = CorporationStorageDemo.load(CORPORATIONSTORAGEDEMOADDRESS3, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            Boolean receipt = contract.initialized().send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询余额
    @Test
    public void CorporationStorageDemo_balanceOf() {
        try {
            this.initialize(deployAccount);
            CorporationStorageDemo contract = CorporationStorageDemo.load(CORPORATIONSTORAGEDEMOADDRESS1, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            BigInteger receipt = contract.balanceOf(corporationCEOAccount1.getAddress()).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //注册公司第一个管理员角色，默认为“CORPORATION_CEO_ROLE”角色
    @Test
    public void CorporationControllerDemo_setRoleAdmin() {
        try {
            this.initialize(corporationCEOAccount2);
            CorporationControllerDemo contract = CorporationControllerDemo.load(CORPORATIONCONTROLLERDEMOADDRESS2, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = contract.setRoleAdmin(CORPORATION_CFO_ROLE,CORPORATION_CEO_ROLE,corporationCFOAccount2.getAddress()).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //检查账户角色
    @Test
    public void CorporationControllerDemo_checkRole() {
        try {
            this.initialize(deployAccount);
            CorporationControllerDemo contract = CorporationControllerDemo.load(CORPORATIONCONTROLLERDEMOADDRESS2, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            Boolean receipt = contract.checkRole(CORPORATION_CFO_ROLE,corporationCFOAccount2.getAddress()).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //取消角色授权
    @Test
    public void CorporationControllerDemo_revokeRole() {
        try {
            this.initialize(deployAccount);
            CorporationControllerDemo contract = CorporationControllerDemo.load(CORPORATIONCONTROLLERDEMOADDRESS3, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = contract.revokeRole(CORPORATION_CEO_ROLE,"0x4dda505b5f042beb3e4e13f4ba4c9ba8bfdabc96").send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //通过代理方法访问UCOBNodeController,发起提议
    @Test
    public void CorporationControllerDemo_startProposal() {
        try {
            this.initialize(corporationCEOAccount2);
            UCOBNodeController contract = UCOBNodeController.load(CORPORATIONCONTROLLERDEMOADDRESS2, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            byte[] proposalId = encodeBySHA256("role");
            byte[] message = encodeBySHA256("message1");
            BigInteger value = new BigInteger("50000");
            TransactionReceipt receipt = contract.startProposal(proposalId,corporationCEOAccount3.getAddress(),CORPORATIONCONTROLLERDEMOADDRESS3,message,value).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通过代理方法访问UCOBNodeController,投票
    @Test
    public void CorporationControllerDemo_voteProposal() {
        try {
            this.initialize(corporationCEOAccount2);
            UCOBNodeController contract = UCOBNodeController.load(CORPORATIONCONTROLLERDEMOADDRESS2, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            byte[] proposalId = encodeBySHA256("role");
            TransactionReceipt receipt = contract.voteProposal(proposalId).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //内部资金转账
    @Test
    public void CorporationControllerDemo_transfer() {
        try {
            this.initialize(corporationCEOAccount2);
            CorporationControllerDemo contract = CorporationControllerDemo.load(CORPORATIONCONTROLLERDEMOADDRESS2, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));

            String to = corporationStaffAccount2.getAddress();
            BigInteger value = new BigInteger("10000");
            TransactionReceipt receipt = contract.transfer(to, value).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //跨公司资金转账，转到指定公司合约地址的指定账户
    @Test
    public void CorporationControllerDemo_transferOut() {
        try {
            this.initialize(corporationStaffAccount2);
            CorporationControllerDemo contract = CorporationControllerDemo.load(CORPORATIONCONTROLLERDEMOADDRESS2, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            BigInteger value = new BigInteger("100");
            TransactionReceipt receipt = contract.transferOut(CORPORATIONCONTROLLERDEMOADDRESS1,corporationCEOAccount1.getAddress(), value).send();
            System.out.println(receipt);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //使用key转账，key可以通过网页端获取，由CFO操作
    @Test
    public void CorporationControllerDemo_transferOutCallback() {
        try {
            byte[] key = changeStringToByte32("0x71bfc6944d4a83169ccb20b659fa9511eefe8b1eb29ea2fc10af37c7c59a222f");
            this.initialize(corporationCFOAccount2);
            CorporationControllerDemo contract = CorporationControllerDemo.load(CORPORATIONCONTROLLERDEMOADDRESS2, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = contract.transferOutCallback(key).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加联合项目管理员
    @Test
    public void CorporationControllerDemo_addUnitedProjectManager() {
        try {
            this.initialize(corporationCEOAccount1);
            UCOBNodeController contract = UCOBNodeController.load(CORPORATIONCONTROLLERDEMOADDRESS1, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = contract.addUnitedProjectManager(CORPORATIONCONTROLLERDEMOADDRESS1,corporationStaffAccount1.getAddress()).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
