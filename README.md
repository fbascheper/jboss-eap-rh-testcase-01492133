# Testcase for RedHat support issue 01492133 on JBoss EAP 6.4.x

This is a simple setup building two docker containers, 

 * PostgreSQL
 * JBoss EAP

Subsequently an Arquillian test is run which fails on the third testcase.

I believe this was already reported as [HHH-7573](https://hibernate.atlassian.net/browse/HHH-7573) 
and [HHH-5255](https://hibernate.atlassian.net/browse/HHH-5255).
