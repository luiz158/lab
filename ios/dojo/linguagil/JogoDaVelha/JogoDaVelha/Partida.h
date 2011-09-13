//
//  Partida.h
//  JogoDaVelha
//
//  Created by Cleverson Sacramento on 12/09/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Jogador.h"

@interface Partida : NSObject
{
    Jogador _proximoJogador;
    Jogador _tabuleiro[3][3];
}

- (Jogador)jogadorDaLinha: (int)linha eColuna: (int)coluna;

- (void)marcarLinha: (int)linha eColuna: (int)coluna;

- (Jogador)proximoJogador;

- (Jogador)vencedorDaPartida;

@end
