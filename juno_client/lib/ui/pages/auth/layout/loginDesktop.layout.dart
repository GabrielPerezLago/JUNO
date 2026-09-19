import 'package:flutter/material.dart';
import 'package:getwidget/components/button/gf_button.dart';
import 'package:getwidget/getwidget.dart';
import 'package:juno_client/config/app/app.tools.dart';
import 'package:juno_client/ui/pages/auth/login.controller.dart';
import 'package:juno_client/ui/widgets/inputs/jninput.widget.dart';
import 'package:juno_client/ui/widgets/loader/loader.widget.dart';
import 'package:juno_client/ui/widgets/wizard/error.wizard.dart';

// ignore: must_be_immutable
class LoginDesktopLayout extends StatefulWidget {
  const LoginDesktopLayout({super.key});


    @override
  State<StatefulWidget> createState() => _LoginDesktopState();
}

class _LoginDesktopState extends State<LoginDesktopLayout> {
  
  bool isRegistrer  = false;
  bool isLoading = false;

  String? errMessage;

  final nombreController = TextEditingController();
  final emailController = TextEditingController();
  final dniController = TextEditingController();
  final passwordController = TextEditingController();
  final repeatPasswordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
  

    return isLoading ? JnLogoLoder() : Scaffold(
    backgroundColor: junoColorScheme(context).primaryFixed,
    body: Center(
      child: SafeArea(child: SingleChildScrollView(
        child: Center(
          child: Container( // Contenedor priencipal 
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(20),
              color: isDark(context) ? Colors.black87 : Colors.white
            ),
            padding: EdgeInsets.all(40),
            width: _containerLoginSize(),
            alignment: Alignment.center,
            child: Column(
              spacing: isRegistrer ? 0.0 : 20,
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Image(
                    image: AssetImage('assets/images/logo.png'),
                    width: width(context) * 0.1,
                  ),
                  Text(
                    'JUNO',
                    style: TextStyle(
                      fontFamily: 'Leckerli',
                      fontSize: width(context) * 0.03,
                      color: junoColorScheme(context).secondary
                    ),
                  ),
                ],
              ),

              _spacingSize(),

              Column(
                spacing: isRegistrer ? 10 : 20,
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  if(isRegistrer) JnInput(controller: nombreController, lblTextTittle: 'Nombre'),
                  JnSizedInput(
                    width: _getInputSize(),
                    controller: emailController, 
                    lblTextTittle: 'Email'
                  ),
                  JnSizedInput(
                    width: _getInputSize(),
                    controller: passwordController, 
                    lblTextTittle: 'Contraseña',
                    isOcultable: true,
                  ),
                  if(isRegistrer) JnInput(controller: repeatPasswordController, lblTextTittle: 'Repetir Contraseña', isOcultable: true,),
                  if (isRegistrer) JnInput(controller: dniController, lblTextTittle: 'DNI/NIF'),
                  if(errMessage != null) Text(errMessage! , style: TextStyle(color: junoColorScheme(context).error, fontSize: width(context) * 0.01)),
                  SizedBox(
                    width: width(context) * 0.3,
                    child: Column(
                      spacing: 10,
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                          GFButton(
                            text: _initSessionString(),
                            onPressed: () async {


                              setState(() {
                                isLoading = true;
                              });
                              final params = { 
                                'email': emailController.text,
                                'password': passwordController.text
                              };
                              final String? msg = LoginViewController.validateLoginArgs(params);

                              if (msg != null){ 
                                setState(() {
                                  isLoading = false ;
                                  errMessage = msg;
                                });
                                return;
                              }


                              final String? isError = await LoginViewController.loginAndRegister(
                                context,
                                isRegistrer ? Type.REGISTER : Type.LOGIN, 
                                params
                              );

                              if (isError != null) {
                                setState(() {
                                  isLoading = false;
                                  errMessage = null;
                                });
                                ErrorWizard.showError(context, isError);
                              }
                            },
                            animationDuration: Duration(seconds: 4),
                            color: junoColorScheme(context).primary,
                            shape: GFButtonShape.pills,
                            size: GFSize.LARGE,
                            fullWidthButton: true,
                            boxShadow: BoxShadow(color: Colors.black),
                          ),
                          GFButton(
                            onPressed: () {
                              setState(() {
                                isRegistrer = !isRegistrer;
                              });
                            },
                            text: isRegistrer ? 'Iniciar Sesión' : 'Registrate',
                            color: junoColorScheme(context).secondary,
                            size: GFSize.LARGE,
                            shape: GFButtonShape.pills,
                          ),
                          GFButton(
                            onPressed: () {},
                            text: '¿ Olvidaste tu contraseña ?',
                            color: Colors.transparent,
                            textColor: junoColorScheme(context).tertiary,
                            shape: GFButtonShape.pills,
                            autofocus: false,
                            animationDuration: Duration( seconds: 0),
                            focusColor: Colors.transparent,
                          )
                      ],
                    ),
                  )
                ]
              ),
            ],
          ),
          ),
        )
      )),
    )
  );
  
  }

  double _containerLoginSize() => width(context) == 800 ? width(context) * 0.8 : width(context) * 0.35 ;
  String _initSessionString() => isRegistrer ? 'Registrate' : 'Iniciar Sesión';
  SizedBox _spacingSize() => SizedBox(height: isRegistrer ? height(context) *  0.1 : height(context) * 0.001,);
  double _getInputSize() => width(context) * 0.5;

  
}