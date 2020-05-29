package com.ucob.controller;

import com.ucob.contract.UCOBNodeController;
import com.ucob.contract.UCOBNodeStorage;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.junit.Test;

import java.math.BigInteger;

/**
 * @Author pai
 * @Description 调用 UCOBController 和 UCOBNodeStorage 合约的控制器类
 * @Date 2020/5/28 11:54
 **/
public class UCOBController extends BaseController{

    //使用部署者地址，转移UCOBNodeStorage合约所有权到UCOBNodeController
    @Test
    public void UCOBNodeStorage_transferOwnership() {
        try {
            this.initialize(deployAccount);
            UCOBNodeStorage contract = UCOBNodeStorage.load(UCOBNODESTORAGEADDRESS, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = contract.transferOwnership(UCOBNODECONTROLLERADDRESS).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //检查所有权是否成功转移
    @Test
    public void UCOBNodeController_checkUCOBNodeStorageSafety() {
        try {
            this.initialize(deployAccount);
            UCOBNodeController contract = UCOBNodeController.load(UCOBNODECONTROLLERADDRESS, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            Boolean receipt = contract.checkUCOBNodeStorageSafety().send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //以“第一家公司”的控制器合约地址作为传参调用“initRoleAdmin()”方法，注册为UCOB中的第一个管理员角色
    @Test
    public void UCOBNodeController_initRoleAdmin() {
        try {
            this.initialize(deployAccount);
            UCOBNodeController contract = UCOBNodeController.load(UCOBNODECONTROLLERADDRESS, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = contract.initRoleAdmin(CORPORATIONCONTROLLERDEMOADDRESS1).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //检查账户角色
    @Test
    public void UCOBNodeController_checkRole() {
        try {
            this.initialize(deployAccount);
            UCOBNodeController contract = UCOBNodeController.load(UCOBNODECONTROLLERADDRESS, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            Boolean receipt = contract.checkRole(CORPORATION_MEMBERS_ROLE,CORPORATIONCONTROLLERDEMOADDRESS2).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //检查提议
    @Test
    public void UCOBNodeController_getProposal() {
        try {
            this.initialize(deployAccount);
            byte[] proposalId = encodeBySHA256("proposal2");
            UCOBNodeController contract = UCOBNodeController.load(UCOBNODECONTROLLERADDRESS, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            Tuple7 receipt = contract.getProposal(proposalId).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //检查某角色的成员
    @Test
    public void UCOBNodeController_getRoleMemberCount() {
        try {
            this.initialize(deployAccount);
            UCOBNodeController contract = UCOBNodeController.load(UCOBNODECONTROLLERADDRESS, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            BigInteger receipt = contract.getRoleMemberCount(CORPORATION_MEMBERS_ROLE).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //给公司赋予UCOB角色
    @Test
    public void UCOBNodeController_grantRole() {
        try {
            this.initialize(deployAccount);
            UCOBNodeController contract = UCOBNodeController.load(UCOBNODECONTROLLERADDRESS, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            byte[] proposalId = encodeBySHA256("role");
            TransactionReceipt receipt = contract.grantRole(proposalId).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //激活公司合约
    @Test
    public void UCOBNodeController_activeCorporation() {
        try {
            this.initialize(deployAccount);
            UCOBNodeController contract = UCOBNodeController.load(UCOBNODECONTROLLERADDRESS, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            byte[] proposalId = encodeBySHA256("proposal2");
            TransactionReceipt receipt = contract.activeCorporation(proposalId).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询余额
    @Test
    public void UCOBNodeController_balanceOf() {
        try {
            this.initialize(deployAccount);
            UCOBNodeController contract = UCOBNodeController.load(UCOBNODECONTROLLERADDRESS, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            BigInteger receipt = contract.balanceOf(CORPORATIONSTORAGEDEMOADDRESS3).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发行资金
    @Test
    public void UCOBNodeController_issue() {
        try {
            this.initialize(deployAccount);
            UCOBNodeController contract = UCOBNodeController.load(UCOBNODECONTROLLERADDRESS, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            byte[] proposalId = encodeBySHA256("proposalId");
            TransactionReceipt receipt = contract.issue(proposalId).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加联合项目
    @Test
    public void UCOBNodeController_addUnitedProject() {
        try {
            this.initialize(deployAccount);
            UCOBNodeController contract = UCOBNodeController.load(UCOBNODECONTROLLERADDRESS, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            byte[] proposalId = encodeBySHA256("proposalId");
            TransactionReceipt receipt = contract.addUnitedProject(proposalId).send();
            System.out.println(receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
