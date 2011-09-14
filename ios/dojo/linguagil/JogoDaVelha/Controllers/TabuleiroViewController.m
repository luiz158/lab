//
//  TabuleiroViewController.m
//  JogoDaVelha
//
//  Created by Cleverson Sacramento on 13/09/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "TabuleiroViewController.h"
#import "CellView.h"
#import "Notifications.h"


@interface TabuleiroViewController (PrivateMethods) <UIAlertViewDelegate>

- (NSString *)proximaImagem;

- (NSString *)proximoJogador;

- (void)atualizaProximoJogador;

- (NSString *)mensagemFinal;

- (void)marcar: (NSNotification *)notification;

@end

    
@implementation TabuleiroViewController

- (NSString *)proximaImagem
{
    return [_partida proximoJogador] == Jogador1 ? @"x.png" : @"o.png";
}

- (NSString *)proximoJogador
{
    return [_partida proximoJogador] == Jogador1 ? @"Jogador 1" : @"Jogador 2";;
}

- (void)atualizaProximoJogador
{
    self.title = [self proximoJogador];
}

- (NSString *)mensagemFinal
{
    NSString * resultado;
    
    switch ([_partida vencedorDaPartida]) {
        case Jogador1:
            resultado = @"O jogador 1 ganhou";
            break;

        case Jogador2:
            resultado = @"O jogador 2 brocou o jogador 1";
            break;

        default:
            resultado = @"Deu empate :P";
    }
    
    return resultado;
}

- (void)marcar: (NSNotification *)notification
{
    CellView *cellView = (CellView *)notification.object;
    
    int linha = cellView.linha;
    int coluna = cellView.coluna;
    
    NSLog(@"Clicou na linha %i e coluna %i.", linha, coluna);
    
    if([_partida jogadorDaLinha:linha eColuna:coluna] == Ninguem){
        NSString * imageName = [self proximaImagem];
        
        [_partida marcarLinha:linha eColuna:coluna];
        cellView.image = [UIImage imageNamed: imageName];
        
        [self atualizaProximoJogador];
    }
    
    if([_partida vencedorDaPartida] != JogoEmAndamento){
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Cabô" message:[self mensagemFinal] delegate:self cancelButtonTitle:nil otherButtonTitles:@"Jogar novamente", nil];
        
        [alert show];
        [alert release];
    }
}

-(void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
    [_partida reiniciar];
    [[NSNotificationCenter defaultCenter] postNotificationName:LimparCelulas object:nil];
}

#pragma mark - View lifecycle

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(marcar:) name:MarcarCelula object:nil];
    _partida = [[Partida alloc] init];
    [self atualizaProximoJogador];
}

- (void)viewDidUnload
{
    [super viewDidUnload];
    
    [_partida release];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:MarcarCelula object:nil];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

@end
