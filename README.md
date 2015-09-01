# Testcase for RedHat support issue 01492133 on JBoss EAP 6.4.x

This is a simple setup building two docker containers, 

 * PostgreSQL
 * JBoss EAP

Subsequently an Arquillian test is run which fails on the third testcase.

I believe this was already reported as [HHH-7573](https://hibernate.atlassian.net/browse/HHH-7573) 
and [HHH-5255](https://hibernate.atlassian.net/browse/HHH-5255).


Prerequisites:

 1. JDK 8, preferably the latest build, currently 1.8.0_60
 2. Maven 3.3.x
 3. Docker / Boot2Docker, preferably the latest build, currently client / server 1.8.1
 
This test can be run by

    git clone https://github.com/fbascheper/jboss-eap-rh-testcase-01492133
    cd jboss-eap-rh-testcase-01492133
    mvn clean verify -Darquillian=remote


Expected output:
     
     -------------------------------------------------------
      T E S T S
     -------------------------------------------------------
     Running nl.famscheper.hhhtest.model.LobEntityPersistIT
     Sep 01, 2015 8:24:40 AM org.jboss.arquillian.protocol.jmx.JMXMethodExecutor invoke
     SEVERE: Failed: nl.famscheper.hhhtest.model.LobEntityPersistIT.loadEntityWithLob
     java.lang.ClassCastException: org.hibernate.bytecode.instrumentation.spi.LazyPropertyInitializer$1 cannot be cast to [B
     	at org.hibernate.type.descriptor.java.PrimitiveByteArrayTypeDescriptor.areEqual(PrimitiveByteArrayTypeDescriptor.java:42)
     	at org.hibernate.type.AbstractStandardBasicType.isEqual(AbstractStandardBasicType.java:209)
     	at org.hibernate.type.AbstractStandardBasicType.getReplacement(AbstractStandardBasicType.java:102)
     	at org.hibernate.type.AbstractStandardBasicType.replace(AbstractStandardBasicType.java:353)
     	at org.hibernate.type.TypeHelper.replace(TypeHelper.java:177)
     	at org.hibernate.event.internal.DefaultMergeEventListener.copyValues(DefaultMergeEventListener.java:401)
     	at org.hibernate.event.internal.DefaultMergeEventListener.entityIsDetached(DefaultMergeEventListener.java:338)
     	at org.hibernate.event.internal.DefaultMergeEventListener.onMerge(DefaultMergeEventListener.java:180)
     	at org.hibernate.event.internal.DefaultMergeEventListener.onMerge(DefaultMergeEventListener.java:86)
     	at org.hibernate.internal.SessionImpl.fireMerge(SessionImpl.java:833)
     	at org.hibernate.internal.SessionImpl.merge(SessionImpl.java:817)
     	at org.hibernate.internal.SessionImpl.merge(SessionImpl.java:821)
     	at org.hibernate.ejb.AbstractEntityManagerImpl.merge(AbstractEntityManagerImpl.java:889)
     	at org.jboss.as.jpa.container.AbstractEntityManager.merge(AbstractEntityManager.java:548)
     	at nl.famscheper.hhhtest.model.LobEntityPersistIT.loadEntityWithLob(LobEntityPersistIT.java:129)
     	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     	at java.lang.reflect.Method.invoke(Method.java:497)
     	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
     	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
     	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
     	at org.jboss.arquillian.junit.Arquillian$8$1.invoke(Arquillian.java:370)
     	at org.jboss.arquillian.container.test.impl.execution.LocalTestExecuter.execute(LocalTestExecuter.java:60)
     	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     	at java.lang.reflect.Method.invoke(Method.java:497)
     	at org.jboss.arquillian.core.impl.ObserverImpl.invoke(ObserverImpl.java:94)
     	at org.jboss.arquillian.core.impl.EventContextImpl.invokeObservers(EventContextImpl.java:99)
     	at org.jboss.arquillian.core.impl.EventContextImpl.proceed(EventContextImpl.java:81)
     	at org.jboss.arquillian.core.impl.ManagerImpl.fire(ManagerImpl.java:145)
     	at org.jboss.arquillian.core.impl.ManagerImpl.fire(ManagerImpl.java:116)
     	at org.jboss.arquillian.core.impl.EventImpl.fire(EventImpl.java:67)
     	at org.jboss.arquillian.container.test.impl.execution.ContainerTestExecuter.execute(ContainerTestExecuter.java:38)
     	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     	at java.lang.reflect.Method.invoke(Method.java:497)
     	at org.jboss.arquillian.core.impl.ObserverImpl.invoke(ObserverImpl.java:94)
     	at org.jboss.arquillian.core.impl.EventContextImpl.invokeObservers(EventContextImpl.java:99)
     	at org.jboss.arquillian.core.impl.EventContextImpl.proceed(EventContextImpl.java:81)
     	at org.jboss.arquillian.test.impl.TestContextHandler.createTestContext(TestContextHandler.java:130)
     	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     	at java.lang.reflect.Method.invoke(Method.java:497)
     	at org.jboss.arquillian.core.impl.ObserverImpl.invoke(ObserverImpl.java:94)
     	at org.jboss.arquillian.core.impl.EventContextImpl.proceed(EventContextImpl.java:88)
     	at org.jboss.arquillian.test.impl.TestContextHandler.createClassContext(TestContextHandler.java:92)
     	at sun.reflect.GeneratedMethodAccessor4.invoke(Unknown Source)
     	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     	at java.lang.reflect.Method.invoke(Method.java:497)
     	at org.jboss.arquillian.core.impl.ObserverImpl.invoke(ObserverImpl.java:94)
     	at org.jboss.arquillian.core.impl.EventContextImpl.proceed(EventContextImpl.java:88)
     	at org.jboss.arquillian.test.impl.TestContextHandler.createSuiteContext(TestContextHandler.java:73)
     	at sun.reflect.GeneratedMethodAccessor3.invoke(Unknown Source)
     	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     	at java.lang.reflect.Method.invoke(Method.java:497)
     	at org.jboss.arquillian.core.impl.ObserverImpl.invoke(ObserverImpl.java:94)
     	at org.jboss.arquillian.core.impl.EventContextImpl.proceed(EventContextImpl.java:88)
     	at org.jboss.arquillian.core.impl.ManagerImpl.fire(ManagerImpl.java:145)
     	at org.jboss.arquillian.test.impl.EventTestRunnerAdaptor.test(EventTestRunnerAdaptor.java:136)
     	at org.jboss.arquillian.junit.Arquillian$8.evaluate(Arquillian.java:363)
     	at org.jboss.arquillian.junit.Arquillian$4.evaluate(Arquillian.java:245)
     	at org.jboss.arquillian.junit.Arquillian.multiExecute(Arquillian.java:422)
     	at org.jboss.arquillian.junit.Arquillian.access$200(Arquillian.java:54)
     	at org.jboss.arquillian.junit.Arquillian$5.evaluate(Arquillian.java:259)
     	at org.jboss.arquillian.junit.Arquillian$7$1.invoke(Arquillian.java:315)
     	at org.jboss.arquillian.container.test.impl.execution.BeforeLifecycleEventExecuter.on(BeforeLifecycleEventExecuter.java:35)
     	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     	at java.lang.reflect.Method.invoke(Method.java:497)
     	at org.jboss.arquillian.core.impl.ObserverImpl.invoke(ObserverImpl.java:94)
     	at org.jboss.arquillian.core.impl.EventContextImpl.invokeObservers(EventContextImpl.java:99)
     	at org.jboss.arquillian.core.impl.EventContextImpl.proceed(EventContextImpl.java:81)
     	at org.jboss.arquillian.test.impl.TestContextHandler.createTestContext(TestContextHandler.java:130)
     	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     	at java.lang.reflect.Method.invoke(Method.java:497)
     	at org.jboss.arquillian.core.impl.ObserverImpl.invoke(ObserverImpl.java:94)
     	at org.jboss.arquillian.core.impl.EventContextImpl.proceed(EventContextImpl.java:88)
     	at org.jboss.arquillian.test.impl.TestContextHandler.createClassContext(TestContextHandler.java:92)
     	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     	at java.lang.reflect.Method.invoke(Method.java:497)
     	at org.jboss.arquillian.core.impl.ObserverImpl.invoke(ObserverImpl.java:94)
     	at org.jboss.arquillian.core.impl.EventContextImpl.proceed(EventContextImpl.java:88)
     	at org.jboss.arquillian.test.impl.TestContextHandler.createSuiteContext(TestContextHandler.java:73)
     	at sun.reflect.GeneratedMethodAccessor3.invoke(Unknown Source)
     	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     	at java.lang.reflect.Method.invoke(Method.java:497)
     	at org.jboss.arquillian.core.impl.ObserverImpl.invoke(ObserverImpl.java:94)
     	at org.jboss.arquillian.core.impl.EventContextImpl.proceed(EventContextImpl.java:88)
     	at org.jboss.arquillian.core.impl.ManagerImpl.fire(ManagerImpl.java:145)
     	at org.jboss.arquillian.core.impl.ManagerImpl.fire(ManagerImpl.java:116)
     	at org.jboss.arquillian.test.impl.EventTestRunnerAdaptor.fireCustomLifecycle(EventTestRunnerAdaptor.java:159)
     	at org.jboss.arquillian.junit.Arquillian$7.evaluate(Arquillian.java:311)
     	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
     	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
     	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
     	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
     	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
     	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
     	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
     	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
     	at org.jboss.arquillian.junit.Arquillian$2.evaluate(Arquillian.java:204)
     	at org.jboss.arquillian.junit.Arquillian.multiExecute(Arquillian.java:422)
     	at org.jboss.arquillian.junit.Arquillian.access$200(Arquillian.java:54)
     	at org.jboss.arquillian.junit.Arquillian$3.evaluate(Arquillian.java:218)
     	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
     	at org.jboss.arquillian.junit.Arquillian.run(Arquillian.java:166)
     	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
     	at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
     	at org.jboss.arquillian.junit.container.JUnitTestRunner.execute(JUnitTestRunner.java:66)
     	at org.jboss.arquillian.protocol.jmx.JMXTestRunner.doRunTestMethod(JMXTestRunner.java:180)
     	at org.jboss.arquillian.protocol.jmx.JMXTestRunner.runTestMethodInternal(JMXTestRunner.java:162)
     	at org.jboss.arquillian.protocol.jmx.JMXTestRunner.runTestMethod(JMXTestRunner.java:120)
     	at org.jboss.as.arquillian.service.ArquillianService$ExtendedJMXTestRunner.runTestMethod(ArquillianService.java:214)
     	at org.jboss.arquillian.protocol.jmx.JMXTestRunner.runTestMethod(JMXTestRunner.java:137)
     	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     	at java.lang.reflect.Method.invoke(Method.java:497)
     	at sun.reflect.misc.Trampoline.invoke(MethodUtil.java:71)
     	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     	at java.lang.reflect.Method.invoke(Method.java:497)
     	at sun.reflect.misc.MethodUtil.invoke(MethodUtil.java:275)
     	at com.sun.jmx.mbeanserver.StandardMBeanIntrospector.invokeM2(StandardMBeanIntrospector.java:112)
     	at com.sun.jmx.mbeanserver.StandardMBeanIntrospector.invokeM2(StandardMBeanIntrospector.java:46)
     	at com.sun.jmx.mbeanserver.MBeanIntrospector.invokeM(MBeanIntrospector.java:237)
     	at com.sun.jmx.mbeanserver.PerInterface.invoke(PerInterface.java:138)
     	at com.sun.jmx.mbeanserver.MBeanSupport.invoke(MBeanSupport.java:252)
     	at com.sun.jmx.interceptor.DefaultMBeanServerInterceptor.invoke(DefaultMBeanServerInterceptor.java:819)
     	at com.sun.jmx.mbeanserver.JmxMBeanServer.invoke(JmxMBeanServer.java:801)
     	at org.jboss.as.jmx.PluggableMBeanServerImpl$TcclMBeanServer.invoke(PluggableMBeanServerImpl.java:1417)
     	at org.jboss.as.jmx.PluggableMBeanServerImpl.invoke(PluggableMBeanServerImpl.java:692)
     	at org.jboss.as.jmx.BlockingNotificationMBeanServer.invoke(BlockingNotificationMBeanServer.java:168)
     	at org.jboss.remotingjmx.protocol.v2.ServerProxy$InvokeHandler.handle(ServerProxy.java:952)
     	at org.jboss.remotingjmx.protocol.v2.ServerCommon$MessageReciever$1$1.run(ServerCommon.java:153)
     	at org.jboss.as.jmx.ServerInterceptorFactory$Interceptor$1.run(ServerInterceptorFactory.java:75)
     	at org.jboss.as.jmx.ServerInterceptorFactory$Interceptor$1.run(ServerInterceptorFactory.java:70)
     	at java.security.AccessController.doPrivileged(Native Method)
     	at javax.security.auth.Subject.doAs(Subject.java:422)
     	at org.jboss.as.controller.AccessAuditContext.doAs(AccessAuditContext.java:94)
     	at org.jboss.as.jmx.ServerInterceptorFactory$Interceptor.handleEvent(ServerInterceptorFactory.java:70)
     	at org.jboss.remotingjmx.protocol.v2.ServerCommon$MessageReciever$1.run(ServerCommon.java:149)
     	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
     	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
     	at java.lang.Thread.run(Thread.java:745)
     
     Tests run: 3, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 22.472 sec <<< FAILURE! - in nl.famscheper.hhhtest.model.LobEntityPersistIT
     loadEntityWithLob(nl.famscheper.hhhtest.model.LobEntityPersistIT)  Time elapsed: 2.288 sec  <<< ERROR!
     java.lang.ClassCastException: org.hibernate.bytecode.instrumentation.spi.LazyPropertyInitializer$1 cannot be cast to [B
     	at org.hibernate.type.descriptor.java.PrimitiveByteArrayTypeDescriptor.areEqual(PrimitiveByteArrayTypeDescriptor.java:42)
     	at org.hibernate.type.AbstractStandardBasicType.isEqual(AbstractStandardBasicType.java:209)
     	at org.hibernate.type.AbstractStandardBasicType.getReplacement(AbstractStandardBasicType.java:102)
     	at org.hibernate.type.AbstractStandardBasicType.replace(AbstractStandardBasicType.java:353)
     	at org.hibernate.type.TypeHelper.replace(TypeHelper.java:177)
     	at org.hibernate.event.internal.DefaultMergeEventListener.copyValues(DefaultMergeEventListener.java:401)
     	at org.hibernate.event.internal.DefaultMergeEventListener.entityIsDetached(DefaultMergeEventListener.java:338)
     	at org.hibernate.event.internal.DefaultMergeEventListener.onMerge(DefaultMergeEventListener.java:180)
     	at org.hibernate.event.internal.DefaultMergeEventListener.onMerge(DefaultMergeEventListener.java:86)
     	at org.hibernate.internal.SessionImpl.fireMerge(SessionImpl.java:833)
     	at org.hibernate.internal.SessionImpl.merge(SessionImpl.java:817)
     	at org.hibernate.internal.SessionImpl.merge(SessionImpl.java:821)
     	at org.hibernate.ejb.AbstractEntityManagerImpl.merge(AbstractEntityManagerImpl.java:889)
     	at org.jboss.as.jpa.container.AbstractEntityManager.merge(AbstractEntityManager.java:548)
     	at nl.famscheper.hhhtest.model.LobEntityPersistIT.loadEntityWithLob(LobEntityPersistIT.java:129)
     
     
     Results :
     
     Tests in error: 
       LobEntityPersistIT.loadEntityWithLob:129 » ClassCast org.hibernate.bytecode.in...
     
     Tests run: 3, Failures: 0, Errors: 1, Skipped: 0
     
     [INFO] 
     [INFO] --- docker-maven-plugin:0.13.2:stop (Stop-docker-images) @ tst-core-model-integration-tests ---
     [INFO] DOCKER> [fbascheper/tst-jboss-eap:1.0-SNAPSHOT] "jboss-eap": Stop and remove container 5c808d99ab26
     [INFO] DOCKER> [fbascheper/tst-postgres-db:1.0-SNAPSHOT] "tst-postgres-db": Stop and remove container 2758fa8cd972
     [INFO] 
     [INFO] --- maven-failsafe-plugin:2.18.1:verify (integration-tests) @ tst-core-model-integration-tests ---
     [INFO] Failsafe report directory: /java/git/hibernate/jboss-eap-rh-testcase-01492133/tst-core-model-integration-tests/target/failsafe-reports
     [INFO] ------------------------------------------------------------------------
     [INFO] Reactor Summary:
     [INFO] 
     [INFO] TST Parent ......................................... SUCCESS [  1.821 s]
     [INFO] TST Build - Complete ............................... SUCCESS [  0.075 s]
     [INFO] TST core model ..................................... SUCCESS [  1.870 s]
     [INFO] TST Docker images - Root ........................... SUCCESS [  0.027 s]
     [INFO] TST Postgres DB image .............................. SUCCESS [  3.759 s]
     [INFO] TST JBoss - docker image ........................... SUCCESS [  1.509 s]
     [INFO] TST core model - integration tests ................. FAILURE [01:06 min]
     [INFO] ------------------------------------------------------------------------
     [INFO] BUILD FAILURE
     [INFO] ------------------------------------------------------------------------
     [INFO] Total time: 01:16 min
     [INFO] Finished at: 2015-09-01T08:24:51+02:00
     [INFO] Final Memory: 60M/565M
     [INFO] ------------------------------------------------------------------------
     [ERROR] Failed to execute goal org.apache.maven.plugins:maven-failsafe-plugin:2.18.1:verify (integration-tests) on project tst-core-model-integration-tests: There are test failures.
     [ERROR] 
     [ERROR] Please refer to /java/git/hibernate/jboss-eap-rh-testcase-01492133/tst-core-model-integration-tests/target/failsafe-reports for the individual test results.
     [ERROR] -> [Help 1]
     [ERROR] 
     [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
     [ERROR] Re-run Maven using the -X switch to enable full debug logging.
     [ERROR] 
     [ERROR] For more information about the errors and possible solutions, please read the following articles:
     [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
     [ERROR] 
     [ERROR] After correcting the problems, you can resume the build with the command
     [ERROR]   mvn <goals> -rf :tst-core-model-integration-tests
