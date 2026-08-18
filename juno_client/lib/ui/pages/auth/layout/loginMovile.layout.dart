import 'package:flutter/material.dart';
import 'package:getwidget/components/button/gf_button.dart';
import 'package:getwidget/getwidget.dart';
import 'package:juno_client/config/app/app.tools.dart';
import 'package:juno_client/domain/entity/SESSION.dart';
import 'package:juno_client/ui/widgets/inputs/app_input.widget.dart';

class LoginMovileLayout extends StatefulWidget {

    @override
  State<StatefulWidget> createState() => _LoginMovileState();
}

class _LoginMovileState extends State<LoginMovileLayout> {


  @override
  Widget build(BuildContext context) => Scaffold(
    body: SafeArea(
        child: Container(
          padding: EdgeInsets.all(20),
          alignment: Alignment.center,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Expanded(
                flex: 2,
                child:  Column(
                   mainAxisAlignment: MainAxisAlignment.center,
                   children: [
                      Image(
                        image: AssetImage('assets/images/logo.png'),
                        width: width(context) * 0.3,
                      ),
                      Text(
                        'JUNO', 
                        style: TextStyle(fontSize: width(context) * 0.15, color: junoColorScheme(context).secondary ),)
                   ],
                )
              ),
              Expanded(
                flex: 2,
                child: Padding(
                  padding: EdgeInsetsGeometry.symmetric(
                    horizontal: width(context) * 0.01,
                    vertical: 20
                  ) ,
                    child: Column(
                      spacing: height(context) * 0.025,
                      children: [
                        Text('INICIAR SESIÓN',
                        style: TextStyle(
                          fontSize: width(context) * 0.08
                        ),),
                        JnInput(
                          controller: TextEditingController(), 
                          isOcutable: false, 
                          lblTextTittle: 'email',
                        ),
                        JnInput(
                          controller: TextEditingController(), 
                          lblTextTittle: 'Contraseña'
                        ),
                        GFButton(
                          shape: GFButtonShape.pills,
                          fullWidthButton: true,
                          size: GFSize.LARGE,
                          text: 'Iniciar Sesion',
                          color: junoColorScheme(context).primary,
                          onPressed: () {},
                          icon: Icon(Icons.lock),
                        )
                      ],
                    ),
                )
              ),
              Expanded(
                flex: 1,
                child: Container())
            ],
          ),
        ),
    ),
  );
}