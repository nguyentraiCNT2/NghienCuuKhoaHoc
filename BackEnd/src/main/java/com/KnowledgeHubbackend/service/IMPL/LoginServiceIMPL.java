package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.algorithm.RandomId;
import com.KnowledgeHubbackend.algorithm.SendCodeByEmail;
import com.KnowledgeHubbackend.algorithm.TokenUtil;
import com.KnowledgeHubbackend.dto.UsersDTO;
import com.KnowledgeHubbackend.entity.RolesEntity;
import com.KnowledgeHubbackend.entity.UserRoleEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import com.KnowledgeHubbackend.repository.LoginRepository;
import com.KnowledgeHubbackend.repository.RolesRepository;
import com.KnowledgeHubbackend.repository.UserRepository;
import com.KnowledgeHubbackend.repository.UserRoleRepository;
import com.KnowledgeHubbackend.service.LoginService;
import jakarta.persistence.EntityNotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceIMPL implements LoginService {
    @Autowired
    private final LoginRepository loginRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RolesRepository rolesRepository;
    @Autowired
    private final UserRoleRepository userRoleRepository;
    @Autowired
    private final ModelMapper modelMapper;
    private UsersEntity userSignUp;
    private UserRoleEntity userrolesignup;
    private String maxacthuc;

    public LoginServiceIMPL(LoginRepository loginRepository, UserRepository userRepository, RolesRepository rolesRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.loginRepository = loginRepository;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }

    @Override // Đăng nhập với tất cả tài khoản
    public UsersDTO loginUser(String username, String password) {
        UsersDTO usersDTO = new UsersDTO();
        //tìm kiếm theo username và password
        List<UsersEntity> usersEntities = userRepository.findByUsername(username);
        if (usersEntities.isEmpty()){
            throw new RuntimeException("tai khoan nay khong ton tai");
        }
        for (UsersEntity useritem: usersEntities
             ) {
            // Kiểm tra mật khẩu nhập vào và mật khẩu mã hóa trong database
            boolean isValid = BCrypt.checkpw(password, useritem.getPassword());
            // Tìm kiếm theo id của user đã tìm kiếm để kiểm tra quyền
            List<UserRoleEntity> userRoleEntity = userRoleRepository.findByUserid(useritem);
            if (isValid){
                for (UserRoleEntity item: userRoleEntity
                ) {
                    if (useritem.getUserid() == item.getUserid().getUserid()
                    ){
                        // chuyển dữ liệu từ cơ sở dữ liệu về dữ liệu chuyển về client
                        usersDTO = modelMapper.map(useritem, UsersDTO.class);
                        usersDTO.setPassword(null);
                    }
                }
            }
            else {
                throw new RuntimeException("mật khẩu không đúng");
            }

        }
        return usersDTO;
    }

    @Override // Đăng nhập vơi cộng tác viên
    public UsersDTO loginCTV(String username, String password) {
        UsersDTO usersDTO = new UsersDTO();
        //tìm kiếm theo username và password
        List<UsersEntity> usersEntities = userRepository.findByUsername(username);
        if (usersEntities.isEmpty()){
            throw new RuntimeException("tai khoan nay khong ton tai");
        }
        for (UsersEntity useritem: usersEntities
        ) {
            // Kiểm tra mật khẩu nhập vào và mật khẩu mã hóa trong database
            boolean isValid = BCrypt.checkpw(password, useritem.getPassword());
            // Tìm kiếm theo id của user đã tìm kiếm để kiểm tra quyền
            List<UserRoleEntity> userRoleEntity = userRoleRepository.findByUserid(useritem);
            if (isValid){
                for (UserRoleEntity item: userRoleEntity
                ) {
                    if (useritem.getUserid() == item.getUserid().getUserid()
                    ){
                        // tìm kiếm theo id role
                        RolesEntity roles = rolesRepository.findByRoleid(item.getRoleid().getRoleid()).orElse(null);

                        if (roles.getRolename().equals("CTV") || roles.getRolename().equals("Admin") ){
                            // chuyển dữ liệu từ cơ sở dữ liệu về dữ liệu chuyển về client
                            usersDTO = modelMapper.map(useritem, UsersDTO.class);
                            usersDTO.setPassword(null);
                        }

                    }
                }
            }
            else {
                throw new RuntimeException("mật khẩu không đúng");
            }

        }
        return usersDTO;
    }

    @Override //Đăng nhập của admin
    public UsersDTO loginAdmin(String username, String password) {
        UsersDTO usersDTO = new UsersDTO();
        //tìm kiếm theo username và password
        List<UsersEntity> usersEntities = userRepository.findByUsername(username);
        if (usersEntities.isEmpty()){
            throw new RuntimeException("tai khoan nay khong ton tai");
        }
        for (UsersEntity useritem: usersEntities
        ) {
            // Kiểm tra mật khẩu nhập vào và mật khẩu mã hóa trong database
            boolean isValid = BCrypt.checkpw(password, useritem.getPassword());
            // Tìm kiếm theo id của user đã tìm kiếm để kiểm tra quyền
            List<UserRoleEntity> userRoleEntity = userRoleRepository.findByUserid(useritem);
            if (isValid){
                for (UserRoleEntity item: userRoleEntity
                ) {
                    if (useritem.getUserid() == item.getUserid().getUserid()
                    ){
                        // tìm kiếm theo id role
                        RolesEntity roles = rolesRepository.findByRoleid(item.getRoleid().getRoleid()).orElse(null);

                        if (roles.getRolename().equals("Admin") ){
                            // chuyển dữ liệu từ cơ sở dữ liệu về dữ liệu chuyển về client
                            usersDTO = modelMapper.map(useritem, UsersDTO.class);
                            usersDTO.setPassword(null);
                        }
                    }
                }
            }
            else {
                throw new RuntimeException("mật khẩu không đúng");
            }

        }
        return usersDTO;
    }

    @Override
    public UsersDTO ComfirmEmail(String code, String userid) {
        if (!maxacthuc.equals(code)){
            throw new RuntimeException("Mã xác thực không đúng");
        }
        if (!userid.equals(userSignUp.getUserid())) {
            throw new RuntimeException("Tài khoản này đã được kích hoạt");
        }
        userRepository.save(userSignUp);
        userRoleRepository.save(userrolesignup);
        UsersDTO dto = modelMapper.map(userSignUp, UsersDTO.class);
        return dto;
    }

    @Override
    public void SignUp(UsersDTO userDTO) {
        if (userDTO != null) {
            List<UsersEntity> phoneUsersEntities = userRepository.findByPhone(userDTO.getPhone());
            List<UsersEntity> emailUsersEntities = userRepository.findByEmail(userDTO.getEmail());
            List<UsersEntity> usersEntities = userRepository.findByUsername(userDTO.getUsername());
            if (!usersEntities.isEmpty()){
                throw new RuntimeException("Tài khoản này đã tồn tại");
            }
            if (emailUsersEntities.size() > 3){
                throw new RuntimeException("Một email chỉ được kích hoạt 3 tài khoản");
            }
            if (phoneUsersEntities.size() > 3){
                throw new RuntimeException("Một số điện thoại chỉ được kích hoạt 3 tài khoản");
            }
            if (userDTO.getPassword().length() < 6) {
                throw new RuntimeException("may khau toi thieu la 6 ki tu");
            } else if (userDTO.getPassword().length() > 20) {
                throw new RuntimeException("may khau toi da la 20 ki tu");
            } else if (userDTO.getPassword().contains(" ")) {
                throw new RuntimeException("may khau khong the chua ky tu trang");
            } else if (userDTO.getPassword().equals(userDTO.getUsername())) {
                throw new RuntimeException("may khau khong the chua ten dang nhap");
            }
            try {
                String MKC2 = RandomId.generateMKC2(4);
                maxacthuc = MKC2;
                String to = userDTO.getEmail();
                String subject = "Xác thực email đăng ký ";
                String name = "Xin chào " + userDTO.getUsername();
                String email = "Có email là: " + userDTO.getEmail();
                String core = "Đây là mã xác thực của bạn: " + MKC2;
                String bottom = "Xin cảm ơn!";
                String body = name + ". \n" + email + ". \n" + core + ". \n" + bottom;
                Boolean test = SendCodeByEmail.sendEmail(to, subject, body);
                if (test == true) {
                    UsersEntity user =  modelMapper.map(userDTO, UsersEntity.class);
                    String hashedPassword =   BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
                    user.setPassword(hashedPassword);
                    List<RolesEntity>  roles = rolesRepository.findByRolename("User");
                    UserRoleEntity userRoleEntity = new UserRoleEntity();
                    userRoleEntity.setRoleid(roles.get(0));
                    userRoleEntity.setUserid(user);
                    if (user != null && userRoleEntity!= null) {
                        userSignUp = user;
                        userrolesignup = userRoleEntity;
                    } else {
                        throw new RuntimeException("Không lấy được dữ liệu!");
                    }
                }
            }catch (Exception e){
                throw new RuntimeException("Email đã tồn tại!");
            }

        }
    }

    @Override
    public UsersDTO getUserById(String token) {
        try {
            String userid = TokenUtil.getUserIdFromToken(token);
            UsersEntity user = userRepository.findByUserid(userid)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + userid));
            return modelMapper.map(user,UsersDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }
}
