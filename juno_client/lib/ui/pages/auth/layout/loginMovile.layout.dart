import 'package:flutter/material.dart';
import 'package:getwidget/getwidget.dart';
import 'package:juno_client/config/app/app.tools.dart';
import 'package:juno_client/ui/pages/auth/login.controller.dart';
import 'package:juno_client/ui/widgets/inputs/jninput.widget.dart';
import 'package:juno_client/ui/widgets/loader/loader.widget.dart';
import 'package:juno_client/ui/widgets/scafolds/basic_scaffold.widget.dart';
import 'package:juno_client/ui/widgets/wizard/error.wizard.dart';

class LoginMovileLayout extends StatefulWidget {
  const LoginMovileLayout({super.key});


    @override
  State<StatefulWidget> createState() => _LoginMovileState();
}

class _LoginMovileState extends State<LoginMovileLayout> {

    bool isLoading = false;
    bool isRegistrer = false;
    String? errString;

    final nombreController = TextEditingController();
    final emailController = TextEditingController();
    final dniController = TextEditingController();
    final passwordController = TextEditingController();
    final repeatPasswordController = TextEditingController();


  @override
  Widget build(BuildContext context) {
    
    return isLoading ? JnLogoLoder() :  JnBasicScaffold(
    context: context,
    backGround: false,
    child: SafeArea(
      child: SingleChildScrollView(
        padding: EdgeInsets.all(20),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            _getSpaceSize(),
            Column(
              spacing: 20,
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Image(
                  image: AssetImage('assets/images/logo.png'),
                  width: width(context) * 0.3,
                ),
                Text(
                  'JUNO', 
                  style: TextStyle(
                    fontSize: width(context) * 0.15, 
                    color: junoColorScheme(context).secondary 
                  ),
                )
              ],
            ),
            _getSpaceSize(),
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                  Padding(
                    padding: EdgeInsetsGeometry.symmetric(
                      horizontal: width(context) * 0.01,
                      vertical: 20
                    ),
                    child: Column(
                      mainAxisSize: MainAxisSize.min ,
                      spacing: height(context) * 0.025,
                      children: [
                        Text( isRegistrer ? '¡ Dinos quien Eres !' : 'INICIAR SESIÓN',
                        style: TextStyle(
                          fontSize: width(context) * 0.07
                        ),),
                        
                        if (isRegistrer) JnInput(controller: 
                          nombreController, 
                          lblTextTittle: 'Nombre Completo',
                          borderColor: borderColorByTheme,
                        ),
                        JnInput(
                          controller: emailController, 
                          lblTextTittle: 'Email',
                          borderColor: borderColorByTheme,
                        ),
                        JnInput(
                          controller: passwordController, 
                          lblTextTittle: 'Contraseña',
                          isOcultable: true,
                          borderColor: borderColorByTheme,
                        ),
                        if (isRegistrer) JnInput(controller: repeatPasswordController, lblTextTittle: 'Repetir Contraseña', borderColor: borderColorByTheme,),
                        if (isRegistrer) JnInput(controller: dniController, lblTextTittle: 'DNI', borderColor: borderColorByTheme),

                        if(errString != null && errString!.isNotEmpty) Text(errString!, style: TextStyle( fontSize: width(context) * 0.01, color: junoColorScheme(context).error)),

                        /* BOTONES */
                        GFButton(
                          shape: GFButtonShape.pills,
                          fullWidthButton: true,
                          size: GFSize.LARGE,
                          text: isRegistrer ? 'Registrate' :'Iniciar Sesion' ,
                          color: junoColorScheme(context).primary,
                          onPressed: () async {
                            setState(() {
                              isLoading = true;
                            });

                            final params = isRegistrer
                              ? {
                                'nombre' : nombreController.text,
                                'email': emailController.text,
                                'password': passwordController.text,
                                'repeatPassword': repeatPasswordController.text,
                                'dni': dniController.text
                              }
                              : { 
                                'email': emailController.text,
                                'password': passwordController.text
                              };
                            final msg = LoginViewController.validateLoginArgs(params);

                            if (msg != null) {
                              setState(() {
                                isLoading = false;
                                errString = msg!;
                              });
                              return;
                            }


                            final String? sign = await LoginViewController.loginAndRegister(
                              context,
                              isRegistrer ? Type.REGISTER : Type.LOGIN ,
                              params
                            );


                            if(sign != null) {
                              setState(() {
                                isLoading = false;
                                errString = null;
                              });
                              ErrorWizard.showError(context, sign);
                            }
                          },
                        ),
                        GFButton(
                          onPressed: () => setState(() {
                            isRegistrer = !isRegistrer;
                          }),
                          shape: GFButtonShape.pills,
                          color: junoColorScheme(context).secondary,
                          text: isRegistrer ? 'Iniciar Sesión' : 'Registrarse',
                          animationDuration: Duration(seconds: 1),
                        ),
                        if (!isRegistrer) GFButton(
                          onPressed: () {},
                          color: Colors.transparent,
                          text: '¿Olvidaste tu Contraseña?',
                          textColor: junoColorScheme(context).tertiary
                        )
                      ],
                    ),
                  )
              ],
            )
          ],
        ),
      ),
  ));
  }

  Color get borderColorByTheme => isDark(context) ? Colors.white60 : Colors.black87;
  SizedBox _getSpaceSize() => SizedBox( height: height(context) * _getSpacingNumberSize(),);
  double _getSpacingNumberSize() => isRegistrer ? 0.05 : 0.1;

  
} 