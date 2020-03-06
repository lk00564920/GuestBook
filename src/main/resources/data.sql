REPLACE INTO `roles` VALUES (1,'ADMIN');
REPLACE INTO `roles` VALUES (2,'GUEST');
REPLACE INTO `users` VALUES (1, true, 'admin@entry.com','User', 'Admin', '$2a$10$VTfQoC71xhrqUTHfXhbQqusmA4SCCWX22x80r2zoHLiI6LXsvVLiC','admin');
REPLACE INTO `users` VALUES (2, true, 'user1@entry.com','User', 'Guest', '$2a$10$MUq7qcHehBMRweA5UYnhoehMAKtglb2OpOpFo0Fg95Ynk7BXdDNlO','user1');
REPLACE INTO `users` VALUES (3, true, 'user2@entry.com','User', 'Guest', '$2a$10$hm5/rvP7zp.GjqgoLdz8z.xdlG2U1qMNRmlr5TSxjEILmgoRRLoju','user2');
REPLACE INTO `user_role` VALUES (1,1);
REPLACE INTO `user_role` VALUES (2,2);
REPLACE INTO `user_role` VALUES (3,2);