
import 'package:flutter/material.dart';
import 'package:juno_client/config/app/WindowConfigProvider.dart';
import 'package:juno_client/config/security/storage.security.dart';
import 'package:juno_client/infraestructure/adapters/AuthAdapter.dart';
import 'package:juno_client/ui/router/pages.router.dart';
import 'package:juno_client/config/theme/JunoThemeProvider.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  try {
    final  windConf = WindowConfigProvider();
    await windConf.exectue();
  } catch (ex) {
    print( 'Error Window Cofiguration : $ex');
  }

  // final bool isLoging = await InitTokenController.loginByToken();

  runApp(JunoClient(true));

}
// ignore: must_be_immutable
class JunoClient extends StatelessWidget {
  late bool _isLoging;

  JunoClient(bool isLoging, { super.key }) {
    this._isLoging = isLoging;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      debugShowCheckedModeBanner: false,
      routerConfig: router(initialDirection: _isLoging ? '/home' : '/login'),
      themeMode: ThemeMode.system,
      theme: JunoThemeProvider.LIGHT,
      darkTheme: JunoThemeProvider.DARK,
    );
  }

}

class InitTokenController {
  static final JnTokenStorageRepository tokenStorage = JnTokenStorageRepository(); 
  static final AuthAdapter adapter = AuthAdapter();

  static Future<bool> loginByToken() async {
    String? token = await tokenStorage.refreshToken;
    if (token == null) return false;
    return await adapter.loginByToken(token);
  }

}