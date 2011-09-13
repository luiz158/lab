//
//  TabuleiroView.m
//  JogoDaVelha
//
//  Created by Cleverson Sacramento on 12/09/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "TabuleiroView.h"

@implementation TabuleiroView

+(NSString *)parse: (Jogador) jogador
{
    return jogador == Jogador1 ? @"Jogador 1" : @"Jogador 2";
}

- (id)initWithCoder:(NSCoder *)coder {
    self = [super initWithCoder:coder];
    if (self) {
        _partida = [[Partida alloc] init];
    }
    return self;
}

-(void)didAddSubview:(UIView *)subview
{
    _label.text = [_partida proximoJogador] ? @"Jogador 1" : @"Jogador 2";    
}

-(void)marcarLinha:(int)linha eColuna:(int)coluna
{
    [_partida marcarLinha:linha eColuna:coluna];
    _label.text = [TabuleiroView parse:[_partida proximoJogador]];
    
    if([_partida vencedorDaPartida] != JogoEmAndamento){
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Vencedor" message:@"???" delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil];
        [alert show];
    }
}

- (BOOL)jogadaPermitidaNaLinha:(int)linha eColuna:(int)coluna
{
    return [_partida jogadorDaLinha:linha eColuna:coluna] == Ninguem;
}

-(NSString *)proximaMarcacao
{
    return [_partida proximoJogador] == Jogador1 ? @"x.png" : @"o.png";
}

- (void)dealloc {
    [_partida release];
    [_label release];
    
    [super dealloc];
}

@end
