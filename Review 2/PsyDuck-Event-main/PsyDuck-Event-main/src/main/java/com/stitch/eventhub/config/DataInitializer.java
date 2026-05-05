package com.stitch.eventhub.config;

import com.stitch.eventhub.entity.Event;
import com.stitch.eventhub.entity.User;
import com.stitch.eventhub.repository.EventRepository;
import com.stitch.eventhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            userRepository.save(User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .role("ADMIN")
                    .name("System Admin")
                    .build());

            userRepository.save(User.builder()
                    .username("student")
                    .password(passwordEncoder.encode("student123"))
                    .role("STUDENT")
                    .name("Senthil Kumar")
                    .build());
        }

        if (eventRepository.count() == 0) {
            eventRepository.save(Event.builder()
                    .title("Anna University Science Symposium")
                    .description("Special seminar on modern technology and scientific discoveries in the heart of Chennai.")
                    .date(LocalDate.of(2024, 10, 15))
                    .department("Anna University")
                    .category("Academic")
                    .capacity(300)
                    .registeredCount(245)
                    .status(Event.EventStatus.UPCOMING)
                    .imageUrl("https://lh3.googleusercontent.com/aida-public/AB6AXuB8HREvLyZn4eV3tXu5N46zjsnvFDCWcANrRXoPRXvgfgcK1L3q9xCJA6AwqseZKHRtjsKs_4g25QM_fE5gZUxJJk0jf8PS3yo2cqxxxzQRHTC3RSQdq-3eJecQhbExVgqpe4LCAFn0OnhkQwvd-peaE6tqpStb9pqvkA6nH3NZ7yo7fHijuVY4p35XkL-sC_Q1q2dQLjmC_QEjGQGhvzsgfLiaVB76tVr_g5-2X3XAkBJl8_NgnGLaFuJyGHiTlbZL3sbCfZiXco0")
                    .coordinator("Prof. Arumugam")
                    .schedule("09:30 AM - Welcome Address; 11:00 AM - Research Paper Presentation; 02:00 PM - Special Guest Lecture; 04:30 PM - Vote of Thanks")
                    .plans("Student inventions will be showcased. Best projects will receive the Young Scientist Award.")
                    .organizerContact("arumugam@annauniv.edu | +91 98400 12345")
                    .build());

            eventRepository.save(Event.builder()
                    .title("Madurai Kamaraj University Cultural Fest")
                    .description("A grand celebration showcasing the rich heritage and culture of Tamil Nadu.")
                    .date(LocalDate.of(2024, 11, 20))
                    .department("Madurai Kamaraj University")
                    .category("Cultural")
                    .capacity(1000)
                    .registeredCount(1000)
                    .status(Event.EventStatus.FULL)
                    .imageUrl("https://lh3.googleusercontent.com/aida-public/AB6AXuDNzId2YRN-M7e0MIFObt1HUchiPHKCvx-Xz8tN9groCFl-MPYDkFk4_sJuSziDmRHn8-LRYYoTpYWi6CQeu7rdF0ukTsuU-rRyBAb_COeWsfXcByUUcuVppr8D_CH1WVSl3JbNDzj_iOwo340wTC-6ClxLZ9cOnkoF7tYm1z7qE0rNldYwcSLrO8j33kmFiYGM5sxI7C4UBtLky17XGgnYVMP8REMUcQEpTFSm9sEIBjgelPgJmLqoVcDHDTGPcTvAlM1we4hiKI0")
                    .coordinator("Prof. Chitra")
                    .schedule("06:00 PM - Tamil Thai Vazhthu; 07:00 PM - Folk Dance Performances; 09:00 PM - Kaviarangam; 11:00 PM - Closing Ceremony")
                    .plans("Traditional arts like Karakattam and Oyilattam will be the main highlights of the evening.")
                    .organizerContact("cultural@mku.ac.in | 0452-2458471")
                    .build());
            
            eventRepository.save(Event.builder()
                    .title("PSG Tech AI & Future Tech Seminar")
                    .description("Technical session on Artificial Intelligence and its future applications in industry.")
                    .date(LocalDate.of(2024, 10, 28))
                    .department("PSG College of Technology")
                    .category("Technical")
                    .capacity(150)
                    .registeredCount(85)
                    .status(Event.EventStatus.UPCOMING)
                    .imageUrl("https://lh3.googleusercontent.com/aida-public/AB6AXuBBRS7Q5ufzm0oyh4v_kZqoAY2v0eyb3oxo4anqDk3l48ZixhlP7-Fun8oYDMZJpeTzldHD7lLn39Xz7aZPqsGYcHmighUZUlCRfG3Vpi9qQ-wzK7KwhZNut1ScFyiuRz3WydjXvIteWczd0cMNIkYsWw0vUC6Ld8c3YN92CsSHbFQDYgVonQc4awVtmciunZt-8PJJ83M5HzvqOFJbEQPaaBbCHMCsTEGQbtS732rcYlzT2AR4LIPin81JfI6TPS8j-RPqzZVV6gI")
                    .coordinator("Prof. Karuppasamy")
                    .schedule("10:00 AM - Introduction to AI; 11:30 AM - Machine Learning Workshop; 02:30 PM - Q&A Session")
                    .plans("Discussion on career opportunities in Robotics and Data Science for final year students.")
                    .organizerContact("karuppasamy@psgtech.edu | Coimbatore")
                    .build());
        }
    }
}
