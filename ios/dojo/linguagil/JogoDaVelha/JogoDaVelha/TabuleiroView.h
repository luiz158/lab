//
//  TabuleiroView.h
//  JogoDaVelha
//
//  Created by Cleverson Sacramento on 12/09/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Partida.h"
#import "Jogador.h"

@interface TabuleiroView : UIImageView
{
    IBOutlet UILabel *_label;
    
    Partida *_partida;
}

- (BOOL)jogadaPermitidaNaLinha: (int)linha eColuna: (int)coluna;

- (void)marcarLinha: (int)linha eColuna:(int)coluna;

- (NSString *)proximaMarcacao;

@end
