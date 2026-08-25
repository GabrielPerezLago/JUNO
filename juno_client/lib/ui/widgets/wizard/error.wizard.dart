import 'package:flutter/material.dart';
import 'package:juno_client/config/app/app.tools.dart';

class ErrorWizard  extends StatelessWidget {
  String errString;

  ErrorWizard({
    required this.errString
  });

  @override
  Widget build(BuildContext context) => Dialog(
    shadowColor: junoColorScheme(context).secondary,
    alignment: Alignment.center,
    backgroundColor: junoColorScheme(context).inversePrimary,
    child: Container(
      width: width(context) * 0.6,
      height: height(context) * 0.6,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Image(
            image: AssetImage('assets/images/error_logo.png'),
            width: width(context) * 0.15,
          ),
          Text(
            '404',
            style: TextStyle(
              color: junoColorScheme(context).primary,
              fontSize: width(context) * 0.06
            ),
          ),
          SizedBox(
            height: height(context) * 0.05,
          ),
          Text(
            errString,
            style: TextStyle(
              color: junoColorScheme(context).error,
              fontSize: width(context) * 0.014
            ),
          ),
        ],
      )
    ));

  static showError( final BuildContext context, final String error) {
    return showDialog(context: context, builder: (context) {
      return ErrorWizard(errString: error);
    });
  }
}