package com.emse.spring.faircorp.model;

import javax.persistence.*;

@Entity
@Table(name = "RWINDOW")
public class Window {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable=false, length=255)
        private String name;

        @Column(nullable=false)
        @Enumerated(EnumType.STRING)
        private WindowStatus windowStatus;

//        @Column(nullable=false)
        @ManyToOne(fetch = FetchType.EAGER)
        private Room room;

        public Window() {
        }

        public Window(Room room, String name, WindowStatus status) {
            this.room  =room;
            this.windowStatus = status;
            this.name = name;
        }

        public Window(String name, WindowStatus status) {
            this.windowStatus = status;
            this.name = name;
        }

        public Long getId() {
            return this.id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public WindowStatus getWindowStatus() {
            return windowStatus;
        }

        public void setWindowStatus(WindowStatus windowStatus) {
            this.windowStatus = windowStatus;
        }
        public Room getRoom() {
            return room;
        }

        public void setRoom(Room room) {
            this.room = room;
        }
}
