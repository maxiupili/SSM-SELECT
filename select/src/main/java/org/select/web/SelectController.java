package org.select.web;

import org.apache.ibatis.annotations.Param;
import org.select.dto.EnrollResult;
import org.select.dto.SelectResult;
import org.select.entitiy.Enrollment;
import org.select.entitiy.Project;
import org.select.entitiy.User;
import org.select.exception.ProjectUpdateException;
import org.select.exception.RepeatSelectException;
import org.select.exception.SelectException;
import org.select.service.EnrollmentService;
import org.select.service.ProjectService;
import org.select.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@RequestMapping("/select")
public class SelectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private EnrollmentService enrollmentService;

    @RequestMapping(value = "/userConsole")
    public String console() {
        return "userConsole";
    }

    @RequestMapping(value = "/projects",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.GET)
    @ResponseBody
    public List<Project> projects() {
        List<Project> list = projectService.getProjectList();
        return list;
    }

    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public String login(@Param(value = "username") String username, @Param(value = "password") String password, HttpSession session) {
        User user = null;
        try {
            user = userService.login(username, password);
            System.out.println("set User" + user);
            session.setAttribute("currentUser", user);
        } catch (Exception e) {
            session.setAttribute("message", e.getMessage());
            return "forward:/select/login";
        }
        if (user.getUsername().equals("admin"))
            return "redirect:/select/adminIndex";

        return "redirect:/select/index";
    }

    @RequestMapping(value = "/enrollment/{id}",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SelectResult enroll(@PathVariable("id") Long projectId, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        System.out.println("注册项目:" + currentUser.getUsername()+projectId);
        Long userId = currentUser.getUserId();
        Enrollment thisEnrollment = new Enrollment();
        thisEnrollment.setUserId(userId);
        thisEnrollment.setProjectId(projectId);
        EnrollResult enrollResult = null;
        try {
            enrollmentService.deleteEnrollmentByUserId(userId);
            enrollResult = enrollmentService.enroll(thisEnrollment);
        } catch (RepeatSelectException e1) {
            return new SelectResult(false, e1.getMessage());
        } catch (SelectException e2) {
            return new SelectResult(false, e2.getMessage());
        } catch (Exception e) {
            return new SelectResult(false, e.getMessage());
        }
        return new SelectResult(true, enrollResult);

    }

    @RequestMapping(value = "/adminConsole")
    public String adminProjectList() {
        return "adminConsole";
    }
    /**
     * /project/{id}  get 查询
     * /project      put 插入
     * /project/{id} delete 删除
     * /project/{id} put 修改
     */
    /**
     * 员工保存
     */

    @RequestMapping(value = "/project/{projectId}", method = RequestMethod.PUT)
    @ResponseBody
    public SelectResult updateProject(Project project) {
        System.out.println("更新的project" + project);
        try {
            projectService.updateProject(project);
        }catch (ProjectUpdateException e){
            return new SelectResult(false,e.getMessage());
        }

        return SelectResult.success();
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    @ResponseBody
    public SelectResult saveProject(Project project) {
        projectService.saveProject(project);
        return SelectResult.success();
    }

    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SelectResult<Project> getProject(@PathVariable("id") Long id) {
        Project project = projectService.getProject(id);
        return new SelectResult<Project>(true, project);
    }

    @RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public SelectResult deleteProject(@PathVariable("id") Long id) {
        System.out.println("待删除项目id:" + id);
        projectService.deleteProject(id);
        return SelectResult.success();
    }

    /*@RequestMapping(value = "/enrollment", method = RequestMethod.DELETE)
    @ResponseBody
    public SelectResult deleteEnrollment(HttpSession session) {
        User u = (User) session.getAttribute("currentUser");
        System.out.println("enrollment delete currentUser:" + u);
        Long id = u.getUserId();
        System.out.println("/enrollment delete"+id);
        enrollmentService.deleteEnrollmentByUserId(id);
        return SelectResult.success();
    }*/

    /* @RequestMapping(value = "/enrollment/{id}",method = RequestMethod.POST)
     @ResponseBody
     public SelectResult enroll(@PathVariable("id")Long projectId,HttpSession session){
         Enrollment thisEnrollment = new Enrollment();
         User u =(User)session.getAttribute("currentUser");
         System.out.println("注册"+u);
         thisEnrollment.setUserId(u.getUserId());
         thisEnrollment.setProjectId(projectId);
         enrollmentService.enroll(thisEnrollment);
         return SelectResult.success();
     }*/
    @RequestMapping(value = "/enrollment", method = RequestMethod.GET)
    @ResponseBody
    public SelectResult getEnrollmentInfo(HttpSession session) {
        User u = (User) session.getAttribute("currentUser");
        System.out.println("查看" + u.getUsername() + "项目");
        Project project = enrollmentService.getProjectByUserId(u.getUserId());
        System.out.println("项目为:" + project.getProjectName());
        return new SelectResult<Project>(true, project);
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";

    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/adminIndex")
    public String adminIndex() {
        return "adminIndex";
    }

    @RequestMapping(value = "/invalidation")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/select/login";
    }
    @RequestMapping(value="/enrollmentList")
    public String enrollmentList(){
        return "enrollmentList";
    }
    @RequestMapping(value="/enrollments")
    @ResponseBody
    public SelectResult enrollments(){
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        return new SelectResult(true,enrollments);
    }


}
