package com.example.moviezone;

import com.example.moviezone.model.Cinema;
import com.example.moviezone.model.Worker;
import com.example.moviezone.service.WorkerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkerService workerService;

    @Test
    @WithMockUser(roles = "USER")
    public void testUserCanAccessPublicEndpoints() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/films"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser(roles = "USER")
    public void testUserHasRoleUserPermissions() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home/getSeats/123"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser(username = "t", roles = "WORKER")
    public void testWorkerHasRoleWorkerPermissions() throws Exception {
        Cinema cinema = new Cinema();
        cinema.setName("test");
        Worker worker = new Worker("t","t","t","t","t","t","t","t","t",cinema);

        when(workerService.getWorkerByUsername("t")).thenReturn(worker);
        mockMvc.perform(MockMvcRequestBuilders.get("/profileWorker"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/addFilm"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
        mockMvc.perform(MockMvcRequestBuilders.get("/myTickets"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAdminHasRoleAdminPermissions() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/addFilm"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser(roles = "USER") // A user without ADMIN role
    public void testUserCannotAccessAdminEndpoints() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/addFilm"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
        mockMvc.perform(MockMvcRequestBuilders.get("/profileWorker"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

    }
}