//  MonitoramentoManager.h

#import <Foundation/Foundation.h>
#import "Monitoramento.h"

@interface MonitoramentoManager : NSObject

+ (BOOL)inserir:(Monitoramento *)monitoramento;
+ (NSArray *)obterPorEmail:(NSString *)email;
+ (BOOL)excluir:(Monitoramento *)monitoramento;

@end
