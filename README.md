# Chat1.0-TCP-
GUI imitate QQ，and achieve TCP communicating.
Finish on 2016.6.22

��1���쳣��java.net.BindException:Address already in use: JVM_Bind�����쳣�����ڷ������˽���new ServerSocket(port)��port��һ��0��65536������ֵ������ʱ���쳣��ԭ������Ϊ��portһ����һ���˿��Ѿ��������������м�������ʱ��netstat �Can������Կ���һ��Listending״̬�Ķ˿ڡ�ֻ��Ҫ��һ��û�б�ռ�õĶ˿ھ��ܽ��������⡣

��2���쳣��java.net.ConnectException: Connection refused: connect�����쳣�����ڿͻ��˽���new Socket(ip, port)����ʱ�����쳣������ԭ���ǻ��߾���ip��ַ�Ļ��������ҵ���Ҳ����˵�ӵ�ǰ���������ڵ�ָ��ip·�ɣ��������Ǹ�ip���ڣ����Ҳ���ָ���Ķ˿ڽ��м��������ָ����⣬���ȼ��ͻ��˵�ip��port�Ƿ�д���ˣ������ȷ��ӿͻ���pingһ�·��������Ƿ���pingͨ�������pingͨ������������˰�ping��������Ҫ����İ취�������ڷ������˵ļ���ָ���˿ڵĳ����Ƿ�����������϶��ܽ��������⡣

��3���쳣��java.net.SocketException: Socket is closed�����쳣�ڿͻ��˺ͷ����������ܷ������쳣��ԭ���Ǽ��������ر������Ӻ󣨵�����Socket��close�������ٶ��������ӽ��ж�д������

��4���쳣��java.net.SocketException: ��Connection reset����Connect reset by peer:Socket write error�������쳣�ڿͻ��˺ͷ������˾��п��ܷ�����������쳣��ԭ������������һ���������һ�˵�Socket���رգ��������رջ�����Ϊ�쳣�˳�������Ĺرգ�����һ���Է������ݣ����͵ĵ�һ�����ݰ��������쳣(Connect reset by peer)����һ����һ���˳������˳�ʱ��δ�رո����ӣ���һ������ڴ������ж��������׳����쳣��Connection reset�����򵥵�˵���������ӶϿ���Ķ���д��������ġ�

��5���쳣��java.net.SocketException: Broken pipe�����쳣�ڿͻ��˺ͷ��������п��ܷ������ڵ�4���쳣�ĵ�һ������У�Ҳ�����׳�SocketExcepton:Connect reset by peer:Socket write error�󣩣�����ټ���д�������׳����쳣��ǰ�����쳣�Ľ������������ȷ�������˳�ǰ�ر����е��������ӣ������Ҫ���Է��Ĺر����Ӳ��������ֶԷ��ر����Ӻ��Լ�ҲҪ�رո����ӡ�

������Java �����̳����ļ����쳣��


com.ljh.password----�ṩ�������ݵļ��ܽ��ܡ�
com.ljheee.socket-----tcp������-�ͻ��˺����ࡣ
com.ljheee.login_ui----��¼����
�������Ϳͻ�����ʹ��һ�µĵ�¼���棬��¼�������붼��554278334,ljheee