package com.kethableez.walkerapi.Admin.Controller;

import java.util.List;

import com.kethableez.walkerapi.Admin.Service.AdminService;
import com.kethableez.walkerapi.Dog.Model.DTO.DogCard;
import com.kethableez.walkerapi.Report.Model.Report;
import com.kethableez.walkerapi.Report.Model.ReportStatus;
import com.kethableez.walkerapi.Report.Service.ReportService;
import com.kethableez.walkerapi.User.Model.DTO.UserWithAdditionalInfo;
import com.kethableez.walkerapi.Utility.Model.Activity;
import com.kethableez.walkerapi.Utility.Response.ActionResponse;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {
    
    private final AdminService adminService;
    private final ReportService reportService;

    @Autowired
    public AdminController(AdminService adminService, ReportService reportService) {
        this.adminService = adminService;
        this.reportService = reportService;
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserWithAdditionalInfo>> getUsers() {
        List<UserWithAdditionalInfo> users = adminService.getUsersList();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/walks")
    public ResponseEntity<List<WalkCard>> getWalks() {
        List<WalkCard> walks = adminService.getWalksList();
        return new ResponseEntity<>(walks, HttpStatus.OK);
    }

    @GetMapping("/dogs")
    public ResponseEntity<List<DogCard>> getDogs() {
        List<DogCard> dogs = adminService.getDogsList();
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }

    @GetMapping("/activities")
    public ResponseEntity<List<Activity>> getActivities() {
        List<Activity> activities = adminService.getUserActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @PostMapping("/block/{userId}")
    public ResponseEntity<?> blockUser(@PathVariable("userId") String userId) {
        ActionResponse response = adminService.blockUserById(userId);
        if (response.isSuccess()) return new ResponseEntity<>(response, HttpStatus.OK);
        else return ResponseEntity.badRequest().body(response.getMessage());
    }

    @PostMapping("/ban/{userId}")
    public ResponseEntity<?> banUser(@PathVariable("userId") String userId) {
        ActionResponse response = adminService.banUserById(userId);
        if (response.isSuccess()) return new ResponseEntity<>(response, HttpStatus.OK);
        else return ResponseEntity.badRequest().body(response.getMessage());
    }

    @PostMapping("/unblock/{userId}")
    public ResponseEntity<?> unblockUser(@PathVariable("userId") String userId) {
        ActionResponse response = adminService.unblockUserById(userId);
        if (response.isSuccess()) return new ResponseEntity<>(response, HttpStatus.OK);
        else return ResponseEntity.badRequest().body(response.getMessage());
    }

    @PostMapping("/unban/{userId}")
    public ResponseEntity<?> unbanUser(@PathVariable("userId") String userId) {
        ActionResponse response = adminService.unbanUserById(userId);
        if (response.isSuccess()) return new ResponseEntity<>(response, HttpStatus.OK);
        else return ResponseEntity.badRequest().body(response.getMessage());
    }

    @GetMapping("/reports/{status}")
    public ResponseEntity<List<Report>> getReportsByStatus(@PathVariable("status") ReportStatus status) {
        List<Report> reports = reportService.getReportsByStatus(status);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @PostMapping("/report/{reportId}/{status}")
    public ResponseEntity<?> changeReportStatus(@PathVariable("reportId") String reportId, @PathVariable("status") ReportStatus status){
        ActionResponse response = reportService.changeReportStatus(reportId, status);
        if (response.isSuccess()) return new ResponseEntity<>(response, HttpStatus.OK);
        else return ResponseEntity.badRequest().body(response.getMessage());
    }
}
