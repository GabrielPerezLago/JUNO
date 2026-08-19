import 'package:flutter/material.dart';
import 'package:getwidget/components/button/gf_button.dart';
import 'package:getwidget/getwidget.dart';
import 'package:juno_client/config/app/app.tools.dart';
import 'package:juno_client/config/theme/schemes/input.decoration.dart';
import 'package:juno_client/config/theme/text/text.sheme.dart';
import 'package:juno_client/domain/entity/SESSION.dart';
import 'package:juno_client/ui/widgets/inputs/app_input.widget.dart';
import 'package:juno_client/ui/widgets/loader/loader.widget.dart';

class LoginMovileLayout extends StatefulWidget {

    @override
  State<StatefulWidget> createState() => _LoginMovileState();
}

class _LoginMovileState extends State<LoginMovileLayout> {
    bool isLoading = false;
    bool isRegistrer = false;




  @override
  Widget build(BuildContext context) => isLoading ? JnLogoLoder() :  Scaffold(
    body: SafeArea(
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
                          TextEditingController(), 
                          lblTextTittle: 'Nombre Completo',
                        ),
                        JnInput(
                          controller: TextEditingController(), 
                          isOcutable: false, 
                          lblTextTittle: 'Email',
                        ),
                        JnInput(
                          controller: TextEditingController(), 
                          lblTextTittle: 'Contraseña'
                        ),
                        if (isRegistrer) JnInput(controller: TextEditingController(), lblTextTittle: 'Repetir Contraseña'),
                        if (isRegistrer) JnInput(controller: TextEditingController(), lblTextTittle: 'DNI'),

                        /* BOTONES */
                        GFButton(
                          shape: GFButtonShape.pills,
                          fullWidthButton: true,
                          size: GFSize.LARGE,
                          text: isRegistrer ? 'Registrate' :'Iniciar Sesion' ,
                          color: junoColorScheme(context).primary,
                          onPressed: () {
                            setState(() {
                              isLoading = true;
                            });
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

  SizedBox _getSpaceSize() => SizedBox( height: height(context) * _getSpacingNumberSize(),);
  double _getSpacingNumberSize() => isRegistrer ? 0.05 : 0.1;
} 