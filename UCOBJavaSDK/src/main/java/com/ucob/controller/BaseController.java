package com.ucob.controller;

import org.fisco.bcos.channel.client.PEMManager;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BaseController
 * @Description
 * @Author Pai
 * @Date 2020/5/27 14:23
 **/
public class BaseController {
    protected Web3j web3j;

    protected Credentials credentials;

    //合约部署者的账户，所有合约均有此账户部署
    protected static Credentials deployAccount = handleAccountParam("src/main/resources/accounts/0x4dda505b5f042beb3e4e13f4ba4c9ba8bfdabc96.pem");

    //UCOBNodeStorage合约地址
    protected static final String UCOBNODESTORAGEADDRESS = "0x0e2882f46342d974e308914672aa98eb7d651c17";
    //UCOBNodeController合约地址
    protected static final String UCOBNODECONTROLLERADDRESS = "0x372a85aca297c0e4b4c251961cded7e6047a33af";

    //ProjectDemoUnited合约地址
    protected static final String PROJECTDEMOUNITEDADDRESS = "0x640c3f2a1e05170e3362bb8003ce24635b05dde3";
    //ProjectDemoSingle合约地址
    protected static final String PROJECTDEMOSINGLEADDRESS = "0x3ff187c1b5ee8c489c2dd5edc551a6e280fed6ae";

    ///////////////////////////////////////////////////////
    //公司1存储合约地址
    protected static final String CORPORATIONSTORAGEDEMOADDRESS1 = "0x39ade292051f8c007ceecd03ca9016a2b8b4b289";
    //公司1控制器合约地址
    protected static final String CORPORATIONCONTROLLERDEMOADDRESS1 = "0xff917d0c0e51aeab3445a51b57a66274dfa2e98c";
    //公司1：ceo角色的账户
    protected static Credentials corporationCEOAccount1 = handleAccountParam("src/main/resources/accounts/0x8bd5b67dd9b157955432cd4fe8731ace6396ac8e.pem");
    //公司1：员工角色的账户
    protected static Credentials corporationStaffAccount1 = handleAccountParam("src/main/resources/accounts/0x86760e871fe78e9ff090b520e6890648d4524f4b.pem");


    ///////////////////////////////////////////////////////
    //公司2存储合约地址
    protected static final String CORPORATIONSTORAGEDEMOADDRESS2 = "0x044d30b97b69cc446382956c1c0c664ad2dac154";
    //公司2控制器合约地址
    protected static final String CORPORATIONCONTROLLERDEMOADDRESS2 = "0x2e4f822d162660613618aa0ffc5f3de5dfd09080";
    //公司2：ceo角色的账户
    protected static Credentials corporationCEOAccount2 = handleAccountParam("src/main/resources/accounts/0x91774cf9ad940a5ad979e69960dd8768d652708d.pem");
    //公司2：cfo角色的账户
    protected static Credentials corporationCFOAccount2 = handleAccountParam("src/main/resources/accounts/0x47b4ed535ead97fb5a80eb0268e88d47ba06bb5f.pem");
    //公司2：员工角色的账户
    protected static Credentials corporationStaffAccount2 = handleAccountParam("src/main/resources/accounts/0xb23b0994e98edf3801809fe3e1dd921b2d077af2.pem");


    ///////////////////////////////////////////////////////
    //公司3存储合约地址
    protected static final String CORPORATIONSTORAGEDEMOADDRESS3 = "0x5d53ce9d501623f842a59c323e78d75cbaa55394";
    //公司3控制器合约地址
    protected static final String CORPORATIONCONTROLLERDEMOADDRESS3 = "0x5b5903ee248e1f0afb3926efbfc192f80bddf1fb";
    //公司3：ceo角色的账户
    protected static Credentials corporationCEOAccount3 = handleAccountParam("src/main/resources/accounts/0xb57c3f2f64891aa1b2903986df39dc2e66179b19.pem");
    //公司3：员工角色的账户
    protected static Credentials corporationStaffAccount3 = handleAccountParam("src/main/resources/accounts/0xc280274090c939eb8014429e2b4f3adc0c660c7d.pem");

    //参考RoleConstat.sol合约
    protected static byte[] CORPORATION_MEMBERS_ROLE = changeStringToByte32("0x7c1e678b41ad287bb69f2311576e46353aa6bbd9258a44bd1f9e1c5348000517");
    protected static byte[] CORPORATION_CEO_ROLE = changeStringToByte32("0x156aacb94a05746ef99564f19467c1b0b51f7c3a36059aa0875c04ed68a14839");
    protected static byte[] CORPORATION_CFO_ROLE = changeStringToByte32("0xce57f738f50e0eca704c0f05cb36d59d97b07d220c27745fbfec4cbc15799279");

    static Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected static BigInteger gasPrice = new BigInteger("30000000");
    protected static BigInteger gasLimit = new BigInteger("30000000");
    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
    public void setWeb3j(Web3j web3j) {
        this.web3j = web3j;
    }

    public void initialize(Credentials role) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Service service = context.getBean(Service.class);
        service.run();

        ChannelEthereumService channelEthereumService = new ChannelEthereumService();
        channelEthereumService.setChannelService(service);
        Web3j web3j = Web3j.build(channelEthereumService, 1);
        setWeb3j(web3j);
        setCredentials(role);

        logger.debug(" web3j is " + web3j + " ,credentials is " + credentials);
    }

    protected static Credentials handleAccountParam(String pemName){
        PEMManager pem = new PEMManager();
        InputStream in = readAccountFile(pemName);
        try {
            pem.load(in);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(" message: {}, e: {}", e.getMessage(), e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    logger.error(" message: {}, e: {}", e.getMessage(), e);
                }
            }
        }
        try {
            ECKeyPair keyPair = pem.getECKeyPair();
            return Credentials.create(keyPair);
        }catch (Exception e){
            System.out.println(e.getMessage());
            logger.error(" message: {}, e: {}", e.getMessage(), e);
        }
        return null;
    }

    private static InputStream readAccountFile(String fileName) {
        try {
            Path p = Paths.get(fileName);
            Path s = Paths.get(fileName).toAbsolutePath();
            return Files.newInputStream(p);
        } catch (IOException e) {
            System.out.println(
                    "["
                            + Paths.get(fileName).toAbsolutePath()
                            + "]"
                            + " cannot be opened because it does not exist.");
        }
        return null;
    }


    protected static byte[] changeStringToByte32(String byte32){
        if(byte32.length()!=66)return null;
        byte32 = byte32.replace("0x","");
        List<String> l = getStrList(byte32,2);
        byte[] bl = new byte[32];
        for(int i =0 ;i< l.size();i++){
            String s1 = l.get(i);
            int a = Integer.parseInt(change16To10(s1));
            byte s2 = (byte)a;
            bl[i] = s2;
        }
        return bl;
    }

    private static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length, size);
    }

    private static List<String> getStrList(String inputString, int length,
                                          int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }

    private static String substring(String str, int f, int t) {
        if (f > str.length())
            return null;
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }

    private static String change16To10(String num) {
        int f = 16;
        int t = 10;
        String str = new BigInteger(num, f).toString(t);
        return  str;
    }

    protected static byte[] encodeBySHA256(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
            messageDigest.update(str.getBytes());
            return messageDigest.digest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
